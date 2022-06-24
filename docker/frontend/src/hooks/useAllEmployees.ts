import { useCallback, useState } from "react";
import { instance } from "../api/axios";
import { typeEmployee } from "../type/typeEmployee";

export const useAllEmployees = () => {
  const [employeesData, setEmployeesData] = useState<Array<typeEmployee>>([]);
  const [loading, setLoading] = useState(false);

  const getEmployees = useCallback(() => {
    setLoading(true);
    instance
      .get<Array<typeEmployee>>("employees")
      .then((res) => {
        setEmployeesData(res.data);
      })
      .catch(() => {})
      .finally(() => setLoading(false));
  }, []);
  return { employeesData, getEmployees, loading };
};
