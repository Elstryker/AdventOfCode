#!/usr/bin/env python

import os

dayNumber = int(input("Day Number: "))

folderName = f'Day_{dayNumber}'

os.mkdir(folderName)

open(folderName + '/input.txt','w').close()
open(folderName + '/shortInput.txt','w').close()
open(folderName + '/main.js','w').close()