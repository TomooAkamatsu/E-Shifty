import { Td, Tr } from "@chakra-ui/react";
import { memo, VFC } from "react";
import { ShiftTableBodyTd } from "./ShiftTableBodyTd";

type Props = {
  employeeName: string;
  shift: { [key: string]: string };
};

export const ShiftTableBodyTr: VFC<Props> = memo((props) => {
  const { employeeName, shift } = props;

  return (
    <Tr>
      <Td py={2} px={3} textAlign="center">
        {employeeName}
      </Td>
      {Object.keys(shift).map((date) => (
        <ShiftTableBodyTd
          shift={shift}
          date={date}
          employeeName={employeeName}
        />
      ))}
    </Tr>
  );
});
