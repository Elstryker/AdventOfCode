import * as help from '../Common/help.js';

// Part 1

const getItemPoints = (letter) => {
  let points = 0;
  const ascii = letter.charCodeAt(0);
  if (letter === letter.toUpperCase()) {
    points = ascii - 64 + 26;
  } else {
    points = ascii - 96;
  }

  return points;
};

const splitRucksack = (rucksack) => {
  const middleIndex = rucksack.length / 2;
  const rucksackArray = [...rucksack];
  const firstContainer = rucksackArray.splice(0, middleIndex);
  const secondContainer = rucksackArray;

  return {
    first: firstContainer,
    second: secondContainer,
  };
};

const getSharingItem = (rucksack) => {
  const firstContainer = {};
  for (const item of rucksack.first) {
    firstContainer[item] = true;
  }

  for (const item of rucksack.second) {
    if (firstContainer[item]) {
      return item;
    }
  }

  return undefined;
};

// Part 2

const splitRucksacksIntoGroups = (rucksacks: any[]) => {
  const groups: any[] = [];

  for (let i = 0; i < Math.ceil(rucksacks.length / 3); i++) {
    groups.push(rucksacks.slice(i * 3, i * 3 + 3));
  }

  return groups;
};

const getCommonItem = (group) => {
  const discoveredItems: any[] = [];

  group.forEach((element, i) => {
    const previous = i - 1;
    const elementItems: any = {};
    let check = true;
    if (previous < 0) {
      check = false;
    }

    for (const item of element) {
      let add = true;
      if (check && !discoveredItems[previous][item]) {
        add = false;
      }

      if (add) {
        elementItems[item] = true;
      }
    }

    discoveredItems.push(elementItems);
  });

  return Object.keys(discoveredItems.at(-1))[0];
};

export function Part1() {
  // const inp = help.readShortInputByDay(3);
  const inp = help.readInputByDay(3);

  let rucksacks = inp.split('\n');
  let total = rucksacks
    .map((x) => splitRucksack(x))
    .map((x) => getSharingItem(x))
    .map((x) => getItemPoints(x))
    .reduce((a, b) => a + b);

  console.log('Part 1: ', total);
}

export function Part2() {
  // const inp = help.readShortInputByDay(3);
  const inp = help.readInputByDay(3);

  let rucksacks = inp.split('\n');
  let total = splitRucksacksIntoGroups(rucksacks)
    .map((x) => getCommonItem(x))
    .map((x) => getItemPoints(x))
    .reduce((a, b) => a + b);

  console.log('Part 2: ', total);
}
