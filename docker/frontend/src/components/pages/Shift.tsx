import { Box } from "@chakra-ui/react";
import { memo, useCallback, VFC } from "react";
import { useHistory } from "react-router-dom";
import { PrimaryButton } from "../atoms/button/PrimaryButton";
import { Table, Thead, Tbody, Tr, Th, Td } from "@chakra-ui/react";
import { dummyShiftData } from "../../dummy/dummyShiftData";

export const Shift: VFC = memo(() => {
  const history = useHistory();

  const onClickVacationRequest = useCallback(
    () => history.push("/shiftwork_management/shift/request"),
    [history]
  );
  const onClickShiftCreation = useCallback(
    () => history.push("/shiftwork_management/shift/new"),
    [history]
  );

  const today = new Date();
  const beginningOfThisMonth = new Date(
    today.getFullYear(),
    today.getMonth(),
    1
  );
  const endOfThisMonth = new Date(today.getFullYear(), today.getMonth() + 1, 0);

  const dateList = [];

  for (
    var d = beginningOfThisMonth;
    d <= endOfThisMonth;
    d.setDate(d.getDate() + 1)
  ) {
    dateList.push(new Date(d));
  }

  return (
    <Box textAlign="center" p={5}>
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
      <PrimaryButton onClick={onClickVacationRequest}>
        休み希望の提出確認
      </PrimaryButton>
      <PrimaryButton onClick={onClickShiftCreation}>
        来月のシフト作成
      </PrimaryButton>
    </Box>
  );
});
