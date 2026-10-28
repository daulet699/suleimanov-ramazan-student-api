#!/usr/bin/env bash
set -euo pipefail

cd "$(dirname "$0")/.."

{
echo "================================"
echo "SULEIMANOV RAMAZAN SYSTEM REPORT"
echo "================================"

echo "Student: Suleimanov Ramazan"
echo "Group: IT2-2308"
echo "Student ID: 37408"

echo "Date and Time:"
date

echo "Current User:"
whoami

echo "Operating System:"
cat /etc/os-release | grep PRETTY_NAME

echo "Kernel Version:"
uname -r

echo "CPU Information:"
uname -m
getconf _NPROCESSORS_ONLN

echo "Memory Information:"
grep -E '^(MemTotal|MemAvailable):' /proc/meminfo

echo "Disk Usage:"
df -h .

echo "Available Disk Space:"
df -Pk . | awk 'NR==2 {printf "%.2f GB\n", $4/1048576}'

} | tee logs/Suleimanov_Ramazan_system_report.log
