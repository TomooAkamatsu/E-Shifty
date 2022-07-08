import { Box, HStack } from "@chakra-ui/react";
import { memo, VFC } from "react";
import { PrimaryButton } from "../atoms/button/PrimaryButton";

type Props = {
  year: number;
  month: number;
  setYear: (i: number) => void;
  setMonth: (i: number) => void;
};

export const MonthlyShiftSelectButton: VFC<Props> = memo((props) => {
  const { year, month, setYear, setMonth } = props;
  const today = new Date();
  const monthOfNowPage = new Date(year, month, 1);

  const lastMonthOfNowPage = new Date(year, month - 1, 1);
  const onClickLastMonth = () => {
    setYear(lastMonthOfNowPage.getFullYear());
    setMonth(lastMonthOfNowPage.getMonth());
  };

  const nextMonthOfNowPage = new Date(year, month + 1, 1);
  const onClickNextMonth = () => {
    setYear(nextMonthOfNowPage.getFullYear());
    setMonth(nextMonthOfNowPage.getMonth());
  };

  return (
    <Box>
      <HStack justify="center">
        <Box>
          <PrimaryButton
            onClick={onClickLastMonth}
          >{`${lastMonthOfNowPage.getFullYear()}年
      ${lastMonthOfNowPage.getMonth() + 1}月`}</PrimaryButton>
        </Box>
        <Box>
          <h1 style={{ fontSize: "20px", fontWeight: "bold" }}>
            {`${monthOfNowPage.getFullYear()}年${
              monthOfNowPage.getMonth() + 1
            }月のシフト`}
          </h1>
        </Box>
        <Box>
          {today.getFullYear() === monthOfNowPage.getFullYear() &&
          today.getMonth() === monthOfNowPage.getMonth() ? (
            <Box w={142}></Box>
          ) : (
            <PrimaryButton
              onClick={onClickNextMonth}
            >{`${nextMonthOfNowPage.getFullYear()}年
      ${nextMonthOfNowPage.getMonth() + 1}月`}</PrimaryButton>
          )}
        </Box>
      </HStack>
    </Box>
  );
});
