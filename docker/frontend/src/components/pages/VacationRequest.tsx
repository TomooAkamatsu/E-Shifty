import { Box } from "@chakra-ui/react";
import { memo, useCallback, useState, VFC } from "react";
import { useHistory } from "react-router-dom";
import { PrimaryButton } from "../atoms/button/PrimaryButton";
import "react-day-picker/dist/style.css";
import { DayPickCalendar } from "../organisms/calendar/DayPickCalendar";
import axios from "axios";

export const VacationRequest: VFC = memo(() => {
  const history = useHistory();
  const onClickShift = useCallback(
    () => history.push("/shiftwork_management/shift"),
    [history]
  );

  const initialDays: Date[] = [];
  const [days, setDays] = useState<Date[] | undefined>(initialDays);

  const postRequestData = {
    employeeId: 1,
    requestDate: days,
  };

  const onClickPostRequest = () => {
    axios
      .post("http://localhost:8080/api/shift/requests", postRequestData)
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <Box align="center" p="5">
      <Box w="350px" textAlign="center">
        <DayPickCalendar days={days} setDays={setDays} />
        <PrimaryButton onClick={onClickShift}>戻る</PrimaryButton>
        <PrimaryButton onClick={onClickPostRequest}>提出</PrimaryButton>
      </Box>
    </Box>
  );
});
