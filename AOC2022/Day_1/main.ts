import * as help from '../Common/help';

const groupElfCalories = (caloriesString) => {
  const allCalories = caloriesString.split('\n');
  const allCaloriesNums = allCalories.map((x) => Number.parseInt(x, 10));
  const summedCalories: number = allCaloriesNums.reduce((acc, current) => acc + current);
  return summedCalories;
};

export function Part1() {
  // let inp = help.readShortInputByDay(1)
  const inp = help.readInputByDay(1);

  let elvesCalories = inp.split('\n\n');

  let elvesCaloriesNumbers = elvesCalories.map(groupElfCalories);

  const mostCalories = Math.max(...elvesCaloriesNumbers);

  console.log('Part 1: ', mostCalories);
}

export function Part2() {
  // let inp = help.readShortInputByDay(1);
  const inp = help.readInputByDay(1);

  let elvesCalories = inp.split('\n\n');

  let elvesCaloriesNumbers = elvesCalories.map(groupElfCalories);

  elvesCaloriesNumbers = elvesCaloriesNumbers.sort((a, b) => a - b).slice(-3);

  console.log('Part 2: ', elvesCaloriesNumbers.reduce((a, b) => a + b));
}
