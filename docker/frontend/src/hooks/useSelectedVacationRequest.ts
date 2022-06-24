import { useCallback, useEffect, useState } from "react";
import { instance } from "../api/axios";
import { useAuthUser } from "../provider/login/AuthUserContext";
import { typeVacationRequestList } from "../type/typeVacationRequestList";

export const useSelectedVacationRequest = () => {
  const [selectedVacationRequest, setSelectedVacationRequest] =
    useState<typeVacationRequestList>();
  const [days, setDays] = useState<Date[] | undefined>([]);
  const authUser = useAuthUser();

  const getSelectedVacationRequest = useCallback(() => {
    const employeeId = authUser?.userId;
    console.log(employeeId);
    instance
      .get<typeVacationRequestList>(`shift/vacation-requests/${employeeId}`)
      .then((res) => {
        setSelectedVacationRequest(res.data);
      })
      .catch(() => {});
  }, [authUser?.userId]);

  useEffect(() => {
    const selectedVacationDate: Array<Date> = [];
    selectedVacationRequest?.requestDate.map((date) =>
      selectedVacationDate.push(
        new Date(
          Number(date.substring(0, 4)),
          Number(date.substring(5, 7)) - 1,
          Number(date.substring(8, 10))
        )
      )
    );
    setDays(selectedVacationDate);
  }, [selectedVacationRequest]);

  return { days, setDays, getSelectedVacationRequest };
};
