import { Box, Center, Spinner } from "@chakra-ui/react";
import { memo, ReactNode, useCallback, useEffect, useState, VFC } from "react";
import { useHistory } from "react-router-dom";
import { PrimaryButton } from "../atoms/button/PrimaryButton";
import { Table, Thead, Tbody, Tr, Th, Td } from "@chakra-ui/react";
import { useShiftList } from "../../hooks/useShiftList";
import { useAuthUser } from "../../provider/login/AuthUserContext";
import { MonthlyShiftSelectButton } from "../molecules/MonthlyShiftSelectButton";
import { ShiftTableDate } from "../atoms/ShiftTableDate";

export const Shift: VFC = memo(() => {
  const history = useHistory();
  const [year, setYear] = useState(new Date().getFullYear());
  const [month, setMonth] = useState(new Date().getMonth());
  const authUser = useAuthUser();

  const { shiftData, getShift, loading, exist } = useShiftList(year, month + 1);

  const onClickVacationRequest = useCallback(
    () => history.push("/shiftwork_management/shift/request"),
    [history]
  );
  const onClickShiftCreation = useCallback(
    () => history.push("/shiftwork_management/shift/new"),
    [history]
  );

  const monthOfNowPage = new Date(year, month, 1);

  const beginningOfTheMonth = new Date(
    monthOfNowPage.getFullYear(),
    monthOfNowPage.getMonth(),
    1
  );
  const endOfTheMonth = new Date(
    monthOfNowPage.getFullYear(),
    monthOfNowPage.getMonth() + 1,
    0
  );

  const dateList = [];
  for (
    var d = beginningOfTheMonth;
    d <= endOfTheMonth;
    d.setDate(d.getDate() + 1)
  ) {
    dateList.push(new Date(d));
  }

  useEffect(() => {
    getShift();
  }, [getShift]);

  return (
    <>
      <Box textAlign="center" p={5}>
        <MonthlyShiftSelectButton
          year={year}
          month={month}
          setYear={setYear}
          setMonth={setMonth}
        />
        {loading ? (
          <Center h="40vh">
            <Spinner size="xl" />
          </Center>
        ) : (
          <>
            {exist ? (
              <Table size="sm" variant="striped" colorScheme="blackAlpha">
                <Thead>
                  <Tr>
                    <Th p={1} textAlign="center">
                      名前
                    </Th>
                    {dateList.map((date, index) => (
                      <ShiftTableDate date={date} key={index} />
                    ))}
                  </Tr>
                </Thead>
                <Tbody>
                  {shiftData.map((shift, index) => (
                    <Tr key={index}>
                      <Td textAlign="center" p={1}>
                        {shift.employeeName}
                      </Td>
                      {shift.patternArr.map((shiftPattern, index) => (
                        <Td textAlign="center" p={1} key={index}>
                          {shiftPattern}
                        </Td>
                      ))}
                    </Tr>
                  ))}
                </Tbody>
              </Table>
            ) : (
              <Center h="30vh">
                <h1 style={{ fontSize: "20px", fontWeight: "bold" }}>
                  シフトが作成されていません
                </h1>
              </Center>
            )}
          </>
        )}
        <PrimaryButton onClick={onClickVacationRequest}>
          休み希望の提出確認
        </PrimaryButton>
        {authUser?.isAdmin && (
          <PrimaryButton onClick={onClickShiftCreation}>
            来月のシフト作成
          </PrimaryButton>
        )}
      </Box>
    </>
  );
});

// const getTableDate = (date: Date, index: number): ReactNode => {
//   if (date.getDay() === 6) {
//     return (
//       <Th key={index} p={1} textAlign="center" color="blue.400">
//         {`${date.getDate()}(${getDayOfWeek(date.getDay())})`}
//       </Th>
//     );
//   }
//   if (date.getDay() === 0) {
//     return (
//       <Th key={index} p={1} textAlign="center" color="red.400">
//         {`${date.getDate()}(${getDayOfWeek(date.getDay())})`}
//       </Th>
//     );
//   }
//   return (
//     <Th key={index} p={1} textAlign="center">
//       {`${date.getDate()}(${getDayOfWeek(date.getDay())})`}
//     </Th>
//   );
// };

// const getDayOfWeek = (i: number): string => {
//   if (i === 1) return "月";
//   if (i === 2) return "火";
//   if (i === 3) return "水";
//   if (i === 4) return "木";
//   if (i === 5) return "金";
//   if (i === 6) return "土";
//   return "日";
// };
