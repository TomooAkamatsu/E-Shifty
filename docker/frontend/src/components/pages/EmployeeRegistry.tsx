import {
  Box,
  FormControl,
  FormLabel,
  HStack,
  Input,
  Select,
  Stack,
} from "@chakra-ui/react";
import axios from "axios";
import { ChangeEvent, memo, useCallback, useState, VFC } from "react";
import { useHistory } from "react-router-dom";
import { PrimaryButton } from "../atoms/button/PrimaryButton";

export const EmployeeRegistry: VFC = memo(() => {
  const history = useHistory();

  const onClickBack = useCallback(() => {
    history.push("/shiftwork_management/employees");
  }, [history]);

  const [newLastName, setNewLastName] = useState<string>("");
  const [newFirstName, setNewFirstName] = useState<string>("");
  const [newRomanLastName, setNewRomanLastName] = useState<string>("");
  const [newRomanFirstName, setNewRomanFirstName] = useState<string>("");
  const [newBirthday, setNewBirthday] = useState<string>("");
  const [newGender, setNewGender] = useState<string>("男");
  const [newAge, setNewAge] = useState<number>(0);
  const [newPhoneNumber, setNewPhoneNumber] = useState<string>("");
  const [newEmail, setNewEmail] = useState<string>("");
  const [newWorkingForm, setNewWorkingForm] = useState<string>("");
  const [newEmploymentDate, setNewEmploymentDate] = useState<string>("");

  const onChangeLastName = (e: ChangeEvent<HTMLInputElement>) => {
    setNewLastName(e.target.value);
  };
  const onChangeFirstName = (e: ChangeEvent<HTMLInputElement>) => {
    setNewFirstName(e.target.value);
  };
  const onChangeRomanLastName = (e: ChangeEvent<HTMLInputElement>) => {
    setNewRomanLastName(e.target.value);
  };
  const onChangeRomanFirstName = (e: ChangeEvent<HTMLInputElement>) => {
    setNewRomanFirstName(e.target.value);
  };
  const onChangeBirthday = (e: ChangeEvent<HTMLInputElement>) => {
    setNewBirthday(e.target.value);
  };
  const onChangeGender = (e: ChangeEvent<HTMLSelectElement>) => {
    setNewGender(e.target.value);
  };
  const onChangeAge = (e: ChangeEvent<HTMLInputElement>) => {
    setNewAge(Number(e.target.value));
  };
  const onChangePhoneNumber = (e: ChangeEvent<HTMLInputElement>) => {
    setNewPhoneNumber(e.target.value);
  };
  const onChangeEmail = (e: ChangeEvent<HTMLInputElement>) => {
    setNewEmail(e.target.value);
  };
  const onChangeWorkingForm = (e: ChangeEvent<HTMLInputElement>) => {
    setNewWorkingForm(e.target.value);
  };
  const onChangeEmploymentDate = (e: ChangeEvent<HTMLInputElement>) => {
    setNewEmploymentDate(e.target.value);
  };

  // const postNewEmployeeDate = {
  //   lastName: { newLastName },
  //   firstName: { newFirstName },
  //   romanLastName: { newRomanLastName },
  //   romanFirstName: { newRomanFirstName },
  //   birthday: { newBirthday },
  //   age: { newAge },
  //   gender: { newGender },
  //   phoneNumber: { newPhoneNumber },
  //   email: { newEmail },
  //   employmentDate: { newEmploymentDate },
  //   workingForm: { newWorkingForm },
  // };
  // console.log(postNewEmployeeDate);

  const onClickRegistry = () => {
    const postNewEmployeeDate = {
      lastName: { newLastName },
      firstName: { newFirstName },
      romanLastName: { newRomanLastName },
      romanFirstName: { newRomanFirstName },
      birthday: { newBirthday },
      age: { newAge },
      gender: { newGender },
      phoneNumber: { newPhoneNumber },
      email: { newEmail },
      employmentDate: { newEmploymentDate },
      workingForm: { newWorkingForm },
    };
    const postNewEmployeeDateArr = [
      { newLastName },
      { newFirstName },
      { newRomanLastName },
      { newRomanFirstName },
      { newBirthday },
      { newAge },
      { newGender },
      { newPhoneNumber },
      { newEmail },
      { newEmploymentDate },
      { newWorkingForm },
    ];
    console.log(postNewEmployeeDateArr);
    axios
      .post("http://localhost:8080/api/employees", postNewEmployeeDate)
      .then((res) => {
        console.log(res);
      });
  };

  return (
    <>
      <Box align="right" pr={30} pt={3}>
        <PrimaryButton onClick={onClickBack}>従業員一覧に戻る</PrimaryButton>
      </Box>
      <Box align="center">
        <Stack
          w="350px"
          bg="white"
          px={8}
          py={5}
          borderRadius="10px"
          shadow="md"
          spacing={4}
        >
          <p style={{ fontWeight: "bold", fontSize: "20px" }}>
            従業員の新規登録
          </p>
          <HStack>
            <FormControl>
              <FormLabel>姓</FormLabel>
              <Input value={newLastName} onChange={onChangeLastName} />
            </FormControl>
            <FormControl>
              <FormLabel>名</FormLabel>
              <Input value={newFirstName} onChange={onChangeFirstName} />
            </FormControl>
          </HStack>
          <HStack>
            <FormControl>
              <FormLabel>姓(ローマ字)</FormLabel>
              <Input
                value={newRomanLastName}
                onChange={onChangeRomanLastName}
              />
            </FormControl>
            <FormControl>
              <FormLabel>名(ローマ字)</FormLabel>
              <Input
                value={newRomanFirstName}
                onChange={onChangeRomanFirstName}
              />
            </FormControl>
          </HStack>
          <HStack>
            <FormControl w={150}>
              <FormLabel>生年月日</FormLabel>
              <Input
                value={newBirthday}
                onChange={onChangeBirthday}
                type="date"
              />
            </FormControl>
            <FormControl w={200}>
              <FormLabel>性別</FormLabel>
              {/* <Input value={newGender} onChange={onChangeGender} /> */}
              <Select value={newGender} onChange={onChangeGender}>
                <option>男</option>
                <option>女</option>
              </Select>
            </FormControl>
            <FormControl w={150}>
              <FormLabel>年齢</FormLabel>
              <Input value={newAge} onChange={onChangeAge} type="number" />
            </FormControl>
          </HStack>
          <FormControl>
            <FormLabel>TEL</FormLabel>
            <Input value={newPhoneNumber} onChange={onChangePhoneNumber} />
          </FormControl>
          <FormControl>
            <FormLabel>Email</FormLabel>
            <Input value={newEmail} onChange={onChangeEmail} />
          </FormControl>
          <HStack pb="3">
            <FormControl>
              <FormLabel>雇用形態</FormLabel>
              <Input value={newWorkingForm} onChange={onChangeWorkingForm} />
            </FormControl>
            <FormControl>
              <FormLabel>雇用開始日</FormLabel>
              <Input
                value={newEmploymentDate}
                onChange={onChangeEmploymentDate}
                type="date"
              />
            </FormControl>
          </HStack>
          <PrimaryButton onClick={onClickRegistry}>登録</PrimaryButton>
        </Stack>
      </Box>
    </>
  );
});
