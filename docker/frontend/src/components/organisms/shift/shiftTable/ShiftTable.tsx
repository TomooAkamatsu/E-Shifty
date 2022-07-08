import { Center, Spinner, Table, Tbody, Th, Thead, Tr } from "@chakra-ui/react";
import { memo, useEffect, VFC } from "react";
import { useDateList } from "../../../../hooks/useDateList";
import { useShiftPatterns } from "../../../../hooks/useShiftPatterns";
import { typeDraft } from "../../../../type/typeDraft";
import { ShiftTableDate } from "../../../atoms/ShiftTableDate";
import { ShiftTableBodyTr } from "./ShitTableBodyTr";

type Props = {
  draft: typeDraft;
  loading: boolean;
};

export const ShiftTable: VFC<Props> = memo((props) => {
  const { firstHalfDateList, latterHalfDateList } = useDateList();
  const { shiftPatterns, getShiftPatterns } = useShiftPatterns();
  const { draft, loading } = props;

  useEffect(() => {
    getShiftPatterns();
  }, [getShiftPatterns]);

  const { firstHalf, latterHalf } = draft;

  return (
    <>
      {loading ? (
        <Center h="80vh">
          <Spinner size="xl" />
        </Center>
      ) : (
        <Table size="sm" variant="striped" colorScheme="blackAlpha">
          <Thead>
            <Tr>
              <Th p={1} textAlign="center">
                名前
              </Th>
              {firstHalfDateList.map((date, index) => (
                <ShiftTableDate date={date} key={index} />
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
                <ShiftTableDate date={date} key={index} />
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
      )}
    </>
  );
});
