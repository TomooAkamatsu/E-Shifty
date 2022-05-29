import { Box } from "@chakra-ui/react";
import { memo, useCallback, VFC } from "react";
import { useHistory } from "react-router-dom";
import { PrimaryButton } from "../atoms/button/PrimaryButton";
import "react-day-picker/dist/style.css";
import { DayPickCalendar } from "../organisms/Calendar/DayPickCalendar";

export const Request: VFC = memo(() => {
  const history = useHistory();

  const onClickShift = useCallback(
    () => history.push("/shiftwork_management/shift"),
    [history]
  );

  return (
    <Box textAlign="center" p="5" width="350px">
      <DayPickCalendar />
      <PrimaryButton onClick={onClickShift}>戻る</PrimaryButton>
      <PrimaryButton onClick={onClickShift}>提出</PrimaryButton>
    </Box>
  );
});
