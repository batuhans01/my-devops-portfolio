pipeline {
    agent any

    parameters {
        choice(
            name: 'NODES',
            choices: [
                '10.255.242.36-qas-web',
                '10.255.242.172-qas-mobile',
                '10.255.242.100-prd-web',
                '10.255.242.101-prd-mobile',
                '10.255.242.48-prd-backoffice',
                '10.255.242.170-dev-mobile',
                '10.255.242.25-dev-web'
            ],
            description: 'Select the node'
        )

        choice(
            name: 'ACTION',
            choices: ['start', 'stop'],
            description: 'Select the action'
        )
    }

    stages {

        stage('Execute Action on Node') {
            steps {
                script {
                    def node = params.NODES
                    def action = params.ACTION

                    def ip = node.split('-')[0]
                    def environment = node.split('-')[1]

                    def su_user = (environment == 'qas')
                                    ? 'ycqadm'
                                    : (environment == 'dev')
                                        ? 'ycdadm'
                                        : 'ycpadm'

                    def hybris_dir = (environment == 'qas' || environment == 'dev')
                                        ? '/hybris/hybris667/hybris/bin/platform'
                                        : '/hybris/hybris/bin/platform'

                    sh """
echo "Connecting to ${ip} as ${su_user}"
ssh root@${ip} '
    echo "Switching to user: ${su_user}"
    sudo -u ${su_user} bash -c "cd ${hybris_dir} && ./hybrisserver.sh ${action}"
'
"""
                }
            }
        }

        stage('Send Slack Notification') {
            steps {
                script {
                    def node = params.NODES
                    def ip = node
