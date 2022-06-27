import { useCallback, useState } from "react";
import { instance } from "../api/axios";
import { typeShift } from "../type/typeShift";

export const useShiftList = (year: number, month: number) => {
  const [shiftData, setShiftData] = useState<Array<typeShift>>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [exist, setExist] = useState<boolean>(false);

  const getShift = useCallback(() => {
    setLoading(true);
    instance
      .get<Array<typeShift>>(`shift/${year}/${month}`)
      .then((res) => {
        setShiftData(res.data);
        setExist(true);
      })
      .catch(() => setExist(false))
      .finally(() => setLoading(false));
  }, [year, month]);

  return { shiftData, getShift, loading, exist };
};
