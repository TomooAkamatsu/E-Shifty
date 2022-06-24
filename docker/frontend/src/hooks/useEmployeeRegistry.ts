import { ChangeEvent, useCallback, useState } from "react";

export const useEmployeeRegistry = () => {
  const [newLastName, setNewLastName] = useState<string>("");
  const [newFirstName, setNewFirstName] = useState<string>("");
  const [newRomanLastName, setNewRomanLastName] = useState<string>("");
  const [newRomanFirstName, setNewRomanFirstName] = useState<string>("");
  const [newBirthday, setNewBirthday] = useState<string>("");
  const [newGender, setNewGender] = useState<string>("");
  const [newAge, setNewAge] = useState<number>(0);
  const [newPhoneNumber, setNewPhoneNumber] = useState<string>("");
  const [newEmail, setNewEmail] = useState<string>("");
  const [newWorkingForm, setNewWorkingForm] = useState<string>("");
  const [newEmploymentDate, setNewEmploymentDate] = useState<string>("");

  const onChangeLastName = useCallback((e: ChangeEvent<HTMLInputElement>) => {
    setNewLastName(e.target.value);
  }, []);
  const onChangeFirstName = useCallback((e: ChangeEvent<HTMLInputElement>) => {
    setNewFirstName(e.target.value);
  }, []);
  const onChangeRomanLastName = useCallback(
    (e: ChangeEvent<HTMLInputElement>) => {
      setNewRomanLastName(e.target.value);
    },
    []
  );
  const onChangeRomanFirstName = useCallback(
    (e: ChangeEvent<HTMLInputElement>) => {
      setNewRomanFirstName(e.target.value);
    },
    []
  );
  const onChangeBirthday = useCallback((e: ChangeEvent<HTMLInputElement>) => {
    setNewBirthday(e.target.value);
  }, []);
  const onChangeGender = useCallback((e: ChangeEvent<HTMLSelectElement>) => {
    setNewGender(e.target.value);
  }, []);
  const onChangeAge = useCallback((e: ChangeEvent<HTMLInputElement>) => {
    setNewAge(Number(e.target.value));
  }, []);
  const onChangePhoneNumber = useCallback(
    (e: ChangeEvent<HTMLInputElement>) => {
      setNewPhoneNumber(e.target.value);
    },
    []
  );
  const onChangeEmail = useCallback((e: ChangeEvent<HTMLInputElement>) => {
    setNewEmail(e.target.value);
  }, []);
  const onChangeWorkingForm = useCallback(
    (e: ChangeEvent<HTMLSelectElement>) => {
      setNewWorkingForm(e.target.value);
    },
    []
  );
  const onChangeEmploymentDate = useCallback(
    (e: ChangeEvent<HTMLInputElement>) => {
      setNewEmploymentDate(e.target.value);
    },
    []
  );
  return {
    newLastName,
    newFirstName,
    newRomanLastName,
    newRomanFirstName,
    newBirthday,
    newGender,
    newAge,
    newPhoneNumber,
    newEmail,
    newWorkingForm,
    newEmploymentDate,
    onChangeLastName,
    onChangeFirstName,
    onChangeRomanLastName,
    onChangeRomanFirstName,
    onChangeBirthday,
    onChangeAge,
    onChangeGender,
    onChangePhoneNumber,
    onChangeEmail,
    onChangeEmploymentDate,
    onChangeWorkingForm,
    setNewGender,
    setNewWorkingForm,
  };
};
