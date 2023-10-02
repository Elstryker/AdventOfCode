import * as help from "../Common/help.js";
import Monkey from "./Monkey.js";

function processMonkeyRounds(monkeys: Monkey[], rounds: number) {
  const monkeyInteractions = Array.from({ length: monkeys.length }, () => 0);

  for (let curRound = 0; curRound < rounds; curRound++) {
    for (let monkeyInd = 0; monkeyInd < monkeys.length; monkeyInd++) {
      const monkey = monkeys[monkeyInd];
      monkey.items.forEach((item) => {
        let worryLevel = item;
        worryLevel = monkey.operation(worryLevel);
        worryLevel = Math.floor(worryLevel / 3);
        const passTest = monkey.testPass(worryLevel);
        let monkeyThatReceivesItem = monkey.testFalse;

        if (passTest) {
          monkeyThatReceivesItem = monkey.testTrue;
        }

        monkeys[monkeyThatReceivesItem].items.push(worryLevel);
        monkeyInteractions[monkeyInd]++;
      });

      monkey.items.splice(0)
    }
  }

  return monkeyInteractions;
}

function processMonkeyRounds2(monkeys: Monkey[], rounds: number) {
  const monkeyInteractions = Array.from({ length: monkeys.length }, () => 0);
  let modulusProduct = 1;
  monkeys.forEach(monkey => {
    modulusProduct *= monkey.test;
  })

  for (let curRound = 0; curRound < rounds; curRound++) {
    for (let monkeyInd = 0; monkeyInd < monkeys.length; monkeyInd++) {
      const monkey = monkeys[monkeyInd];
      monkey.items.forEach((item) => {
        let worryLevel = item;
        worryLevel = monkey.operation(worryLevel);
        const passTest = monkey.testPass(worryLevel);
        let monkeyThatReceivesItem = monkey.testFalse;

        if (passTest) {
          monkeyThatReceivesItem = monkey.testTrue;
        }

        // Numbers too big to be stored, so we simplify by using the modulus of the product of all divisibles
        worryLevel %= modulusProduct;
        monkeys[monkeyThatReceivesItem].items.push(worryLevel);
        monkeyInteractions[monkeyInd]++;
      });

      monkey.items.splice(0)
    }
  }

  return monkeyInteractions;
}

function calculateMonkeyBusiness(monkeyInteractions: number[]) {
  const twoMostInteractions = monkeyInteractions.sort((a, b) => b - a).slice(0, 2);

  return twoMostInteractions[0] * twoMostInteractions[1];
}

export function Part1() {
  // const inp = help.readShortInputByDay(11);
  const inp = help.readInputByDay(11);
  const monkeyInputs = inp.split("\n\n");
  const monkeys = monkeyInputs.map((monkeyInput) => new Monkey(monkeyInput));
  const monkeyInteractions = processMonkeyRounds(monkeys, 20);
  const result = calculateMonkeyBusiness(monkeyInteractions);

  console.log("Part 1:", result);
}

export function Part2() {
  // const inp = help.readShortInputByDay(11);
  const inp = help.readInputByDay(11);
  const monkeyInputs = inp.split("\n\n");
  const monkeys = monkeyInputs.map((monkeyInput) => new Monkey(monkeyInput));
  const monkeyInteractions = processMonkeyRounds2(monkeys, 10000);
  const result = calculateMonkeyBusiness(monkeyInteractions);

  console.log("Part 2:", result);
}
