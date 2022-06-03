import { useCallback, useState } from "react";
import { instance } from "../api/axios";
import { typeEmployee } from "../type/typeEmployee";

export const useAllEmployees = () => {
  const [employeesData, setEmployeesData] = useState<Array<typeEmployee>>([]);

  const getEmployees = useCallback(() => {
    instance
      .get<Array<typeEmployee>>("employees")
      .then((res) => {
        setEmployeesData(res.data);
      })
      .catch(() => {});
  }, []);
  return { employeesData, getEmployees };
};
