import { Box } from "@chakra-ui/react";
import { memo, useCallback, VFC } from "react";
import { useHistory } from "react-router-dom";
import { PrimaryButton } from "../atoms/button/PrimaryButton";
import "react-day-picker/dist/style.css";
import { DayPickCalendar } from "../organisms/Calendar/DayPickCalendar";
import { useSelectedVacationRequest } from "../../hooks/useSelectedVacationRequest";
import { instance } from "../../api/axios";
import { useAuthUser } from "../../provider/login/AuthUserContext";
import { useMessage } from "../../hooks/useMessage";

type typeRequestDate = {
  employeeId: number;
  requestDate: Array<string>;
};

export const VacationRequest: VFC = memo(() => {
  const history = useHistory();
  const authUser = useAuthUser();
  const { showMessage } = useMessage();
  const onClickShift = useCallback(
    () => history.push("/shiftwork_management/shift"),
    [history]
  );

  const { days, getSelectedVacationRequest, setDays } =
    useSelectedVacationRequest();

  const onClickPostRequest = () => {
    const requestDateJSON: typeRequestDate = {
      employeeId: Number(authUser?.userId),
      requestDate: [],
    };
    days?.map((day) =>
      requestDateJSON.requestDate.push(
        `${day.getFullYear()}/${day.getMonth() + 1}/${day.getDate()}`
      )
    );
    instance
      .post(
        `/shift/vacation-requests/${requestDateJSON.employeeId}`,
        requestDateJSON
      )
      .then((res) => {
        console.log(res.data);
        if (res.data.result === "false") {
          instance
            .put(
              `/shift/vacation-requests/${requestDateJSON.employeeId}`,
              requestDateJSON
            )
            .then((res) => {
              console.log(res.data);
              showMessage({
                title: "休み希望日を更新しました",
                status: "success",
              });
              history.push("/shiftwork_management/shift");
            });
        }
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <Box align="center" p="5">
      <h1>休み希望日を選択してください</h1>
      <Box w="350px" textAlign="center">
        <DayPickCalendar
          days={days}
          setDays={setDays}
          getSelectedVacationRequest={getSelectedVacationRequest}
        />
        <PrimaryButton onClick={onClickShift}>戻る</PrimaryButton>
        <PrimaryButton onClick={onClickPostRequest}>提出</PrimaryButton>
      </Box>
    </Box>
  );
});
