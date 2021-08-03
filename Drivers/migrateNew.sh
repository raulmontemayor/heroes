#!/bin/bash
if [ $# -gt 0 ]; then  
  cdate="$(date +%Y%m%d%H%M%S)"
  filename="${cdate}_$1.sql"
  touch src/main/resources/db/changelog/v1/${filename}
  echo -e "--liquibase formatted sql\n" > src/main/resources/db/changelog/v1/${filename}
  echo "--changeset ${USER}:${cdate}_1" >> src/main/resources/db/changelog/v1/${filename}
  echo "src/main/resources/db/changelog/v1/${filename}"

  echo "  - include:" >> src/main/resources/db/changelog/db.changelog-master.yaml
  echo "     file: v1/${filename}" >> src/main/resources/db/changelog/db.changelog-master.yaml
  echo "     relativeToChangelogFile: true" >> src/main/resources/db/changelog/db.changelog-master.yaml
else
    echo "Uso: ./migranteNew NAME"
fi


