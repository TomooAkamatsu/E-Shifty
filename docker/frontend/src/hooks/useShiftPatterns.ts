import { useCallback, useState } from "react";
import { instance } from "../api/axios";
import { typeShiftPatterns } from "../type/typeShiftPatterns";

export const useShiftPatterns = () => {
  const [shiftPatterns, setShiftPatterns] = useState<Array<typeShiftPatterns>>(
    []
  );

  const getShiftPatterns = useCallback(() => {
    instance
      .get<Array<typeShiftPatterns>>("shift/patterns")
      .then((res) => {
        setShiftPatterns(res.data);
      })
      .catch(() => {});
  }, []);
  return { shiftPatterns, getShiftPatterns };
};
