import { Td, Tr } from "@chakra-ui/react";
import { memo, VFC } from "react";
import { typeShiftPatterns } from "../../../../type/typeShiftPatterns";
import { ShiftTableBodyTd } from "./ShiftTableBodyTd";

type Props = {
  employeeName: string;
  shift: { [key: string]: string };
  shiftPatterns: Array<typeShiftPatterns>;
};

export const ShiftTableBodyTr: VFC<Props> = memo((props) => {
  const { employeeName, shift, shiftPatterns } = props;

  // 月の日数で列の数を調整
  // バグやばそう
  const date31th = [0, 2, 4, 6, 7, 9, 11];
  const date30th = [3, 5, 8, 10];
  const now = new Date();
  const nextMonth = new Date(now.getFullYear(), now.getMonth() + 1, 1);

  let correctedShiftValue: Array<string> = [];
  let correctedShiftKey: Array<string> = [];
  let correctedShift: { [key: string]: string } = {};

  if (Object.keys(shift).includes("date1st")) {
    correctedShift = shift;
  }

  if (Object.keys(shift).includes("date16th")) {
    if (date31th.indexOf(nextMonth.getMonth())) {
      correctedShift = shift;
    }

    if (date30th.indexOf(nextMonth.getMonth())) {
      correctedShiftValue = Object.values(shift).filter(
        (x, index) => index < 15
      );
      correctedShiftKey = Object.keys(shift).filter((x, index) => index < 15);
      for (var i = 0; i < 15; i++) {
        correctedShift[correctedShiftKey[i]] = correctedShiftValue[i];
      }
    }

    // 閏年の処理がめんどくさいので4で割り切れると一律で29日
    if (nextMonth.getMonth() === 2) {
      if (nextMonth.getFullYear() % 4 === 0) {
        correctedShiftValue = Object.values(shift).filter(
          (x, index) => index < 14
        );
        correctedShiftKey = Object.keys(shift).filter((x, index) => index < 14);
        for (var y = 0; y < 14; y++) {
          correctedShift[correctedShiftKey[y]] = correctedShiftValue[y];
        }
      }
      if (nextMonth.getFullYear() % 4 !== 0) {
        correctedShiftValue = Object.values(shift).filter(
          (x, index) => index < 13
        );
        correctedShiftKey = Object.keys(shift).filter((x, index) => index < 13);
        for (var x = 0; x < 13; x++) {
          correctedShift[correctedShiftKey[x]] = correctedShiftValue[x];
        }
      }
    }
  }

  return (
    <Tr>
      <Td py={2} px={3} textAlign="center">
        {employeeName}
      </Td>
      {Object.keys(correctedShift).map((date, index) => (
        <ShiftTableBodyTd
          key={index}
          shift={shift}
          date={date}
          employeeName={employeeName}
          shiftPatterns={shiftPatterns}
        />
      ))}
    </Tr>
  );
});
