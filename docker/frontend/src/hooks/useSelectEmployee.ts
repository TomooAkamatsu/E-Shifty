import { useCallback, useState } from "react";
import { dummyEmployeeList } from "../dummy/dummyEmployeeList";
import { typeEmployee } from "../type/typeEmployee";

type Props = {
  employeeId: number;
  onOpen: () => void;
};

export const useSelectEmployee = () => {
  const [selectedEmployee, setSelectedEmployee] = useState<typeEmployee | null>(
    null
  );

  const onSelectEmployee = useCallback((props: Props) => {
    const { employeeId, onOpen } = props;
    const targetEmployee = dummyEmployeeList.find(
      (employee) => employee.employeeId === employeeId
    );
    // setSelectedEmployee(targetEmployee ?? null);
    onOpen();
  }, []);

  return { onSelectEmployee, selectedEmployee };
};
