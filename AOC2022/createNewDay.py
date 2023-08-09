#!/usr/bin/env python

import os

dayNumber = int(input("Day Number: "))

folderName = f'Day_{dayNumber}'

os.mkdir(folderName)

templateCode = ("import * as help from '../Common/help.js';\n\n"
  'export function Part1() {\n'
  f'  // const inp = help.readShortInputByDay({dayNumber});\n'
  f'  // const inp = help.readInputByDay({dayNumber});\n'
  '}\n\n'

'export function Part2() {\n'
  f'  // const inp = help.readShortInputByDay({dayNumber});\n'
  f'  // const inp = help.readInputByDay({dayNumber});\n'
'}\n'
)

open(folderName + '/input.txt','w').close()
open(folderName + '/shortInput.txt','w').close()
  
with open(folderName + '/main.ts','w') as f:
  f.write(templateCode)