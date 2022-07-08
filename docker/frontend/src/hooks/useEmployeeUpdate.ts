import { ChangeEvent, useCallback, useState } from "react";

export const useEmployeeUpdate = () => {
  const [newLastName, setNewLastName] = useState<string | null>(null);
  const [newFirstName, setNewFirstName] = useState<string | null>(null);
  const [newRomanLastName, setNewRomanLastName] = useState<string | null>(null);
  const [newRomanFirstName, setNewRomanFirstName] = useState<string | null>(
    null
  );
  const [newBirthday, setNewBirthday] = useState<string | null>(null);
  const [newGender, setNewGender] = useState<string | null>(null);
  const [newAge, setNewAge] = useState<number | null>(null);
  const [newPhoneNumber, setNewPhoneNumber] = useState<string | null>(null);
  const [newEmail, setNewEmail] = useState<string | null>(null);
  const [newWorkingForm, setNewWorkingForm] = useState<string | null>(null);
  const [newEmploymentDate, setNewEmploymentDate] = useState<string | null>(
    null
  );

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
