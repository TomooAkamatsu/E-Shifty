import { useCallback, useState } from "react";
import { instance } from "../api/axios";
import { typeShift } from "../type/typeShift";

export const useShiftList = (year: number, month: number) => {
  const [shiftData, setShiftData] = useState<Array<typeShift>>([]);
  const [loading, setLoading] = useState<boolean>(false);

  const getShift = useCallback(() => {
    setLoading(true);
    instance
      .get<Array<typeShift>>(`shift/${year}/${month}`)
      .then((res) => {
        setShiftData(res.data);
      })
      .catch(() => {})
      .finally(() => setLoading(false));
  }, [year, month]);

  return { shiftData, getShift, loading };
};
