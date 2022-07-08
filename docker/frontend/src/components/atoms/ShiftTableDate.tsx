import { Th } from "@chakra-ui/react";
import { memo, VFC } from "react";

type Props = {
  date: Date;
};

export const ShiftTableDate: VFC<Props> = memo((props) => {
  const { date } = props;
  if (date.getDay() === 6) {
    return (
      <Th p={1} textAlign="center" color="blue.400">
        {`${date.getDate()}(${getDayOfWeek(date.getDay())})`}
      </Th>
    );
  }
  if (date.getDay() === 0) {
    return (
      <Th p={1} textAlign="center" color="red.400">
        {`${date.getDate()}(${getDayOfWeek(date.getDay())})`}
      </Th>
    );
  }
  return (
    <Th p={1} textAlign="center">
      {`${date.getDate()}(${getDayOfWeek(date.getDay())})`}
    </Th>
  );
});

const getDayOfWeek = (i: number): string => {
  if (i === 1) return "月";
  if (i === 2) return "火";
  if (i === 3) return "水";
  if (i === 4) return "木";
  if (i === 5) return "金";
  if (i === 6) return "土";
  return "日";
};
