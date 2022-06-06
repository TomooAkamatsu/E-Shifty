import { useCallback, useEffect, useState } from "react";
import { instance } from "../api/axios";
import { typeWorkingForm } from "../type/typeWorkingForm";

export const useWorkingFormList = () => {
  const [workingFormData, setWorkingFormData] = useState<
    Array<typeWorkingForm>
  >([]);
  const [workingFormNameList, setWorkingFormNameList] = useState<Array<string>>(
    []
  );

  const getWorkingFormData = useCallback(() => {
    instance
      .get<Array<typeWorkingForm>>("employees/working-form")
      .then((res) => {
        setWorkingFormData(res.data);
      })
      .catch(() => {});
  }, []);

  useEffect(() => {
    const workingFormNameList: Array<string> = [];
    workingFormData.map((workingForm) =>
      workingFormNameList.push(workingForm.workingFormName)
    );
    setWorkingFormNameList(workingFormNameList);
  }, [workingFormData]);

  return { workingFormNameList, getWorkingFormData };
};
