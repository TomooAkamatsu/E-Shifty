import { Table, Tbody, Th, Thead, Tr } from "@chakra-ui/react";
import { memo, VFC } from "react";
import { dummyNextMonthShiftData } from "../../../../dummy/dummyNextMonthShiftData";
import { useDateList } from "../../../../hooks/useDateList";
import { ShiftTableBodyTr } from "./ShitTableBodyTr";

export const ShiftTable: VFC = memo(() => {
  const { firstHalfDateList, latterHalfDateList } = useDateList();
  const { shiftOfFirstHalfThisMonth, shiftOfLatterHalfThisMonth } =
    dummyNextMonthShiftData;

  return (
    <Table size="sm" variant="striped" colorScheme="blackAlpha">
      <Thead>
        <Tr>
          <Th p={1} textAlign="center">
            名前
          </Th>
          {firstHalfDateList.map((date) => (
            <Th p={1} textAlign="center">{`${
              date.getMonth() + 1
            }/${date.getDate()}`}</Th>
          ))}
        </Tr>
      </Thead>
      <Tbody>
        {shiftOfFirstHalfThisMonth.map((personalShift) => (
          <ShiftTableBodyTr
            employeeName={personalShift.employeeName}
            shift={personalShift.shift}
            key={personalShift.employeeName}
          />
        ))}
      </Tbody>
      <Thead>
        <Tr>
          <Th pt={5} textAlign="center">
            名前
          </Th>
          {latterHalfDateList.map((date) => (
            <Th pt={5} textAlign="center">{`${
              date.getMonth() + 1
            }/${date.getDate()}`}</Th>
          ))}
        </Tr>
      </Thead>
      <Tbody>
        {shiftOfLatterHalfThisMonth.map((personalShift) => (
          <ShiftTableBodyTr
            employeeName={personalShift.employeeName}
            shift={personalShift.shift}
            key={personalShift.employeeName}
          />
        ))}
      </Tbody>
    </Table>
  );
});
