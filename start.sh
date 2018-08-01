#! /bin/bash
docker run  --name newtech-boot -d -p 8080:8080  -v /newtech/imgupload/:/newtech/imgupload/ -v /newtech/springboot/logs/:/newtech/springboot/logs/ newtech-boot:v1