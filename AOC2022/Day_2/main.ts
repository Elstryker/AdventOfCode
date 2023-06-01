import * as help from '../Common/help';

// Win is 6 points
// Draw is 3 points
// Lose is 0 points

// Rock (X, A) - 1 point
// Paper (Y, B) - 2 points
// Scissors (Z, C) - 3 points

const playPossibilities: any = {
  A: {
    X: 3,
    Y: 6,
    Z: 0,
  },
  B: {
    X: 0,
    Y: 3,
    Z: 6,
  },
  C: {
    X: 6,
    Y: 0,
    Z: 3,
  },
};

const playNecessities: any = {
  X: 0,
  Y: 3,
  Z: 6,
};

const processInput = (input: string) => {
  const plays = input.split('\n');
  return plays.map((x: string) => x.split(' '));
};

const usedPlayOutput = (playerPlayed: any) => {
  switch (playerPlayed) {
    case 'X':
      return 1;
    case 'Y':
      return 2;
    case 'Z':
      return 3;
    default:
      return 0;
  }
};

const playPointsOutput = (opponentPlayed: string, playerPlayed: string) => {
  const playPoints = playPossibilities[opponentPlayed][playerPlayed];
  const usedPlay = usedPlayOutput(playerPlayed);

  return playPoints + usedPlay;
};

const convertToPoints1 = (plays: any[]) => plays.map((x: any[]) => {
  const opponentPlayed = x[0];
  const playerPlayed = x[1];

  return playPointsOutput(opponentPlayed, playerPlayed);
});

const convertToPoints2 = (plays: any[]) => plays.map((x: any[]) => {
  const opponentPlayed = x[0];
  const playerPlayed = x[1];

  const value = playNecessities[playerPlayed];

  const play = Object.keys(playPossibilities[opponentPlayed]).find(
    (key) => playPossibilities[opponentPlayed][key] === value,
  )!;

  return playPointsOutput(opponentPlayed, play);
});

export function Part1() {
  // const inp = help.readShortInputByDay(2);
  const inp = help.readInputByDay(2);

  let plays = processInput(inp);
  plays = convertToPoints1(plays);
  const sum = plays.reduce((a: any, b: any) => a + b, 0);

  console.log('Part 1: ', sum);
}

export function Part2() {
  // const inp = help.readShortInputByDay(2);
  const inp = help.readInputByDay(2);

  let plays = processInput(inp);
  plays = convertToPoints2(plays);
  const sum = plays.reduce((a: any, b: any) => a + b, 0);

  console.log('Part 2: ', sum);
}
