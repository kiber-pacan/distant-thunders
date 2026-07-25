#!/bin/bash

echo "-------------------------------"
echo "------------BUILDING-----------"
echo "-------------------------------"
mkdir -p buildAllJars | true
y=5

for i in $(seq 13 $END); do
    sh gradlew :fabric:build :fabric:modrinth :fabric:publishCurseForge -Pindex="$y"

    if [ "$y" -gt 5 ]; then
        sh gradlew :neoforge:build :neoforge:modrinth :neoforge:publishCurseForge -Pindex="$y"
    else
        sh gradlew :forge:build :forge:modrinth :forge:publishCurseForge -Pindex="$y"
    fi

    mv ./*/build/libs/distantthunders-*-[!c]*-*[[:digit:]].jar "buildAllJars"
    ((y=y+1))
done



echo "-------------------------------"
echo "--------------DONE-------------"
echo "-------------------------------"
