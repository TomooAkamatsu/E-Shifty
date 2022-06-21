import { Table, Tbody, Th, Thead, Tr } from "@chakra-ui/react";
import { memo, useEffect, VFC } from "react";
import { useDateList } from "../../../../hooks/useDateList";
import { useDraft } from "../../../../hooks/useDraft";
import { useShiftPatterns } from "../../../../hooks/useShiftPatterns";
import { ShiftTableBodyTr } from "./ShitTableBodyTr";

export const ShiftTable: VFC = memo(() => {
  const { firstHalfDateList, latterHalfDateList } = useDateList();
  const { shiftPatterns, getShiftPatterns } = useShiftPatterns();
  const { draft, getDraft } = useDraft();

  useEffect(() => {
    getShiftPatterns();
  }, [getShiftPatterns]);

  useEffect(() => {
    getDraft();
  }, [getDraft]);

  console.log(draft);
  const { firstHalf, latterHalf } = draft;

  return (
    <Table size="sm" variant="striped" colorScheme="blackAlpha">
      <Thead>
        <Tr>
          <Th p={1} textAlign="center">
            名前
          </Th>
          {firstHalfDateList.map((date, index) => (
            <Th p={1} textAlign="center" key={index}>{`${
              date.getMonth() + 1
            }/${date.getDate()}`}</Th>
          ))}
        </Tr>
      </Thead>
      <Tbody>
        {firstHalf.map((personalShift, index) => (
          <ShiftTableBodyTr
            employeeName={personalShift.employeeName}
            shift={personalShift.shift}
            key={index}
            shiftPatterns={shiftPatterns}
          />
        ))}
      </Tbody>
      <Thead>
        <Tr>
          <Th pt={5} textAlign="center">
            名前
          </Th>
          {latterHalfDateList.map((date, index) => (
            <Th pt={5} textAlign="center" key={index}>{`${
              date.getMonth() + 1
            }/${date.getDate()}`}</Th>
          ))}
        </Tr>
      </Thead>
      <Tbody>
        {latterHalf.map((personalShift, index) => (
          <ShiftTableBodyTr
            employeeName={personalShift.employeeName}
            shift={personalShift.shift}
            key={index}
            shiftPatterns={shiftPatterns}
          />
        ))}
      </Tbody>
    </Table>
  );
});
