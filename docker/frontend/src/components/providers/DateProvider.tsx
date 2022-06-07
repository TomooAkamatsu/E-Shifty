import { createContext, ReactNode, useState } from "react";

type Props = {
  children: ReactNode;
};

export const DateContext = createContext(
  {} as {
    year: number;
    setYear: (year: number) => void;
    month: number;
    setMonth: (month: number) => void;
  }
);

export const DateProvider = (props: Props) => {
  const { children } = props;
  const [year, setYear] = useState<number>(new Date().getFullYear());
  const [month, setMonth] = useState<number>(new Date().getMonth());

  return (
    <DateContext.Provider value={{ year, setYear, month, setMonth }}>
      {children}
    </DateContext.Provider>
  );
};
