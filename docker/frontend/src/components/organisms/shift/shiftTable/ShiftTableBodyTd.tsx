import { Select, Td } from "@chakra-ui/react";
import { ChangeEvent, memo, VFC } from "react";
import { instance } from "../../../../api/axios";
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
      targetEmployeeName: employeeName,
      targetDate: date,
      changedPattern: e.target.value,
    };
    console.log(postChangedShiftData);
    instance
      .patch("/shift/draft", JSON.stringify(postChangedShiftData))
      .then((r) => console.log(r.data));
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
