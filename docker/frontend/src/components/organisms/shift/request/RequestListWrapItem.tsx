import { Box, WrapItem } from "@chakra-ui/react";
import { memo, useEffect, useState, VFC } from "react";
import { DayPicker } from "react-day-picker";
import "react-day-picker/dist/style.css";
import { typeVacationRequestList } from "../../../../type/typeVacationRequestList";

export const RequestListWrapItem: VFC<typeVacationRequestList> = memo(
  (props) => {
    const { employeeId, employeeName, requestDate } = props;
    const initialDays: Date[] = [];
    const [days, setDays] = useState<Date[] | undefined>(initialDays);

    useEffect(() => {
      const requestDateForSet: Array<Date> = [];
      requestDate.map((date) =>
        requestDateForSet.push(
          new Date(
            Number(date.substring(0, 4)),
            Number(date.substring(5, 7)) - 1,
            Number(date.substring(8, 10))
          )
        )
      );
      setDays(requestDateForSet);
    }, [requestDate]);

    const today = new Date();
    const beginningOfNextMonth = new Date(
      today.getFullYear(),
      today.getMonth() + 1,
      1
    );

    return (
      <WrapItem>
        <Box
          w="370px"
          h="430px"
          bg="white"
          borderRadius="10px"
          shadow="md"
          p={8}
          _hover={{ cursor: "pointer", opacity: 0.8 }}
        >
          <Box px={4}>
            <h1 style={{ fontSize: "20px" }}>
              {`従業員No: ${employeeId}`}&emsp;
              <span
                style={{ fontWeight: "bold", fontSize: "25px" }}
              >{`${employeeName}`}</span>
            </h1>
          </Box>
          <style>{css}</style>
          <DayPicker
            mode="multiple"
            min={0}
            selected={days}
            disableNavigation
            defaultMonth={beginningOfNextMonth}
            modifiersClassNames={{
              selected: "my-selected",
            }}
          />
        </Box>
      </WrapItem>
    );
  }
);

const css = `
.my-selected:not([disabled]) {
  font-weight: bold;
  border: 1px solid currentColor;
  background-color: #FF9999;
}
.my-selected:hover:not([disabled]) {
  border-color: blue;
  color: blue;
  background-color: #FF9999;
}
`;
