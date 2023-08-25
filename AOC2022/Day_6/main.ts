import * as help from '../Common/help.js';

function allDifferentArray(arr: any[]) {
  const visited = new Set();
  for (const ele of arr) {
    if(visited.has(ele)) {
      return false;
    }

    visited.add(ele);
  }

  return true;
}

export function Part1() {
  // const inp = help.readShortInputByDay(6);
  const inp = help.readInputByDay(6);

  const selectedChars = Array.from(inp.slice(0,4));
  let finalIndex = -1;

  for (let i = 4; i < inp.length; i++) {
    const letter = inp[i];
    if(allDifferentArray(selectedChars)) {
      finalIndex = i;
      break;
    }

    selectedChars.shift();
    selectedChars.push(letter);
  }
  

  console.log('Part 1:', finalIndex);
}

export function Part2() {
  // const inp = help.readShortInputByDay(6);
  const inp = help.readInputByDay(6);

  const selectedChars = Array.from(inp.slice(0,14));
  let finalIndex = -1;

  for (let i = 14; i < inp.length; i++) {
    const letter = inp[i];
    if(allDifferentArray(selectedChars)) {
      finalIndex = i;
      break;
    }

    selectedChars.shift();
    selectedChars.push(letter);
  }
  

  console.log('Part 2:', finalIndex);
}
