import { memo, VFC } from "react";
import { DayPicker } from "react-day-picker";
import "react-day-picker/dist/style.css";

type Props = {
  days: Date[] | undefined;
  setDays: (days: Date[] | undefined) => void;
};

export const DayPickCalendar: VFC<Props> = memo((props) => {
  const { days, setDays } = props;

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

  const date = new Date();
  date.setDate(1);
  date.setMonth(date.getMonth() + 1);
  const defaultMonth = date;

  const startDate = new Date(defaultMonth);
  const endDate = new Date(defaultMonth);
  endDate.setMonth(endDate.getMonth() + 1);
  endDate.setDate(0);

  const dateList = [];

  for (var d = startDate; d <= endDate; d.setDate(d.getDate() + 1)) {
    const e = new Date(d);
    dateList.push(e);
  }

  const weekends = dateList.filter((x) => x.getDay() === 0 || x.getDay() === 6);

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
        defaultMonth={defaultMonth}
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
