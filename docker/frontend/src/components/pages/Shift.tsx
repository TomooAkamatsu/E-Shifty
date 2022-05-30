import { Box } from "@chakra-ui/react";
import { memo, useCallback, VFC } from "react";
import { useHistory } from "react-router-dom";
import { PrimaryButton } from "../atoms/button/PrimaryButton";
import { Table, Thead, Tbody, Tr, Th, Td } from "@chakra-ui/react";
import { dummyShiftData } from "../../Dummy/dummyShiftData";

export const Shift: VFC = memo(() => {
  const history = useHistory();
  const onClickRequest = useCallback(
    () => history.push("/shiftwork_management/shift/request"),
    [history]
  );

  const startDate = new Date();
  startDate.setDate(1);
  const endDate = new Date();
  endDate.setMonth(endDate.getMonth() + 1);
  endDate.setDate(0);

  const dateList = [];

  for (var d = startDate; d <= endDate; d.setDate(d.getDate() + 1)) {
    dateList.push(new Date(d));
  }

  return (
    <Box textAlign="center" p="5">
      <h1 style={{ fontSize: "20px", fontWeight: "bold" }}>今月のシフト</h1>
      <Table size="sm" variant="striped" colorScheme="blackAlpha">
        <Thead>
          <Tr>
            <Th p={1}>名前</Th>
            {dateList.map((date) => (
              <Th p={1}>{`${date.getMonth() + 1}/${date.getDate()}`}</Th>
            ))}
          </Tr>
        </Thead>
        <Tbody>
          {dummyShiftData.map((shift) => (
            <Tr>
              {Object.values(shift).map((e) => (
                <Td p={1} textAlign="center">
                  {e}
                </Td>
              ))}
            </Tr>
          ))}
        </Tbody>
      </Table>
      <PrimaryButton onClick={onClickRequest}>休み希望の提出確認</PrimaryButton>
    </Box>
  );
});
