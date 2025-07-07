#!/bin/bash

set_environment() {
  export AWS_DEFAULT_OUTPUT="text"

  export LOCALSTACK=${LOCALSTACK_HOSTNAME:="localhost"}
  export REGION="${LOCALSTACK_REGION:="us-east-1"}"
  export ACCOUNT="123456789012"

  aws configure set aws_access_key_id "${AWS_ACCESS_KEY}"
  aws configure set aws_secret_access_key "${AWS_SECRET_ACCESS_KEY}"
  aws configure set region "${AWS_DEFAULT_REGION}"
}

create_queue() {
  local queue_name=${1}

  SQS_URL="http://${LOCALSTACK}:4566"
  SQS="aws --endpoint-url=${SQS_URL} --region=${REGION} sqs"


  local queue_url=`${SQS} create-queue --queue-name "${queue_name}"`
  echo "[queue] ${queue_url}"
}

main() {
  set_environment

  create_queue "PARTNER_CREATED_QUEUE"

  echo "Localstack is ready!"
}

main