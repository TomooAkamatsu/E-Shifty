import { memo, useEffect, VFC } from "react";
import { DayPicker } from "react-day-picker";
import "react-day-picker/dist/style.css";

type Props = {
  days: Date[] | undefined;
  setDays: (days: Date[] | undefined) => void;
  getSelectedVacationRequest: () => void;
};

export const DayPickCalendar: VFC<Props> = memo((props) => {
  const { days, setDays, getSelectedVacationRequest } = props;

  const footer =
    days && days.length > 0 ? (
      <p>
        <span style={{ fontSize: "20px", fontWeight: "bold" }}>
          {days.length}日
        </span>{" "}
        選択されています
      </p>
    ) : (
      <p>休み希望日を選択してください</p>
    );

  const today = new Date();
  const beginningOfNextMonth = new Date(
    today.getFullYear(),
    today.getMonth() + 1,
    1
  );
  const endOfNextMonth = new Date(today.getFullYear(), today.getMonth() + 2, 0);

  const dateList = [];

  for (
    var d = new Date(beginningOfNextMonth);
    d <= endOfNextMonth;
    d.setDate(d.getDate() + 1)
  ) {
    const e = new Date(d);
    dateList.push(e);
  }
  const weekends = dateList.filter((x) => x.getDay() === 0 || x.getDay() === 6);

  useEffect(() => {
    getSelectedVacationRequest();
  }, [getSelectedVacationRequest]);

  return (
    <>
      <style>{css}</style>
      <DayPicker
        mode="multiple"
        min={0}
        selected={days}
        onSelect={setDays}
        footer={footer}
        disabled={weekends}
        defaultMonth={beginningOfNextMonth}
        disableNavigation
        modifiersClassNames={{
          selected: "my-selected",
        }}
      />
    </>
  );
});

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
