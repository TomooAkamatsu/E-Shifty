import { Select, Td } from "@chakra-ui/react";
import axios from "axios";
import { ChangeEvent, memo, VFC } from "react";
import { typeShiftPatterns } from "../../../../type/typeShiftPatterns";

type Props = {
  employeeName: string;
  date: string;
  shift: { [key: string]: string };
  shiftPatterns: Array<typeShiftPatterns>;
};

export const ShiftTableBodyTd: VFC<Props> = memo((props) => {
  const { shift, date, employeeName, shiftPatterns } = props;

  const onChangeShiftPattern = (e: ChangeEvent<HTMLSelectElement>) => {
    const postChangedShiftData = {
      employeeName: employeeName,
      changeDate: date,
      changedPattern: e.target.value,
    };
    axios.post("", postChangedShiftData).then((res) => {});
  };

  return (
    <Td p={1} textAlign="center">
      <Select
        defaultValue={shift[date]}
        onChange={onChangeShiftPattern}
        size="1px"
        textAlign="center"
      >
        {shiftPatterns.map((pattern, index) => (
          <option key={index}>{pattern.shiftPatternName}</option>
        ))}
      </Select>
    </Td>
  );
});
