import { Box, Center, Spinner, Wrap } from "@chakra-ui/react";
import { memo, useCallback, useEffect, VFC } from "react";
import { useHistory } from "react-router-dom";
import { PrimaryButton } from "../atoms/button/PrimaryButton";
import { RequestListWrapItem } from "../organisms/shift/request/RequestListWrapItem";
import { useVacationRequestList } from "../../hooks/useVacationRequestList";

export const VacationRequestList: VFC = memo(() => {
  const history = useHistory();
  const { vacationRequestList, getVacationRequestList, loading } =
    useVacationRequestList();

  const onClickBack = useCallback(() => {
    history.push("/shift/draft");
  }, [history]);

  useEffect(() => {
    getVacationRequestList();
  }, [getVacationRequestList]);

  return (
    <>
      {loading ? (
        <Center h="80vh">
          <Spinner size="xl" />
        </Center>
      ) : (
        <Box py={5} px={20}>
          <Box align="right" pr={30} pb={5}>
            <Box float="left" px={10} py={5}>
              <p style={{ fontSize: "20px" }}>
                休み希望を出していない人は表示されません
              </p>
            </Box>
            <PrimaryButton onClick={onClickBack}>
              シフト作成画面に戻る
            </PrimaryButton>
          </Box>

          <Box>
            <Wrap spacing="20px" p={{ base: 4, md: 10 }}>
              {vacationRequestList.map((request) => {
                return (
                  <RequestListWrapItem
                    employeeId={request.employeeId}
                    employeeName={request.employeeName}
                    requestDate={request.requestDate}
                    key={request.employeeId}
                  />
                );
              })}
            </Wrap>
          </Box>
        </Box>
      )}
    </>
  );
});
