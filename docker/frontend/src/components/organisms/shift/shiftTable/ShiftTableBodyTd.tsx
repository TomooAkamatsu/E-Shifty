import { Select, Td } from "@chakra-ui/react";
import axios from "axios";
import { ChangeEvent, memo, VFC } from "react";

type Props = {
  employeeName: string;
  date: string;
  shift: { [key: string]: string };
};

export const ShiftTableBodyTd: VFC<Props> = memo((props) => {
  const { shift, date, employeeName } = props;

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
        <option>A</option>
        <option>B</option>
        <option>休み</option>
      </Select>
    </Td>
  );
});
