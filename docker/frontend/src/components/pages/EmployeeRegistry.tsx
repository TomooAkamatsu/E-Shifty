import {
  Box,
  FormControl,
  FormLabel,
  HStack,
  Input,
  Select,
  Stack,
} from "@chakra-ui/react";
import { memo, useCallback, useEffect, VFC } from "react";
import { useHistory } from "react-router-dom";
import { instance } from "../../api/axios";
import { useEmployeeRegistry } from "../../hooks/useEmployeeRegistry";
import { useMessage } from "../../hooks/useMessage";
import { useWorkingFormList } from "../../hooks/useWorkingFormList";
import { PrimaryButton } from "../atoms/button/PrimaryButton";

export const EmployeeRegistry: VFC = memo(() => {
  const history = useHistory();
  const { showMessage } = useMessage();
  const { workingFormNameList, getWorkingFormData } = useWorkingFormList();
  const {
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
  } = useEmployeeRegistry();

  const onClickBack = useCallback(() => {
    history.push("/employees");
  }, [history]);

  const onClickRegistry = () => {
    const postNewEmployeeData = {
      lastName: newLastName,
      firstName: newFirstName,
      romanLastName: newRomanLastName,
      romanFirstName: newRomanFirstName,
      birthday: newBirthday,
      age: newAge,
      gender: newGender,
      phoneNumber: newPhoneNumber,
      email: newEmail,
      employmentDate: newEmploymentDate,
      workingFormName: newWorkingForm,
    };
    console.log(postNewEmployeeData);
    instance
      .post("/employees", JSON.stringify(postNewEmployeeData))
      .then((r) => {
        console.log(r.data);
        showMessage({
          title: "従業員の新規登録に成功しました",
          status: "success",
        });
        history.push("/employees");
      })
      .catch(() => {
        showMessage({
          title: "従業員の新規登録に失敗しました",
          status: "error",
        });
      });
  };

  useEffect(() => {
    getWorkingFormData();
  }, [getWorkingFormData]);

  useEffect(() => {
    setNewGender("男");
    setNewWorkingForm("正社員");
  }, [setNewGender, setNewWorkingForm]);

  return (
    <>
      <Box align="right" pr={30} pt={3}>
        <PrimaryButton onClick={onClickBack}>従業員一覧に戻る</PrimaryButton>
      </Box>
      <Box align="center" pb={5}>
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
              <Input placeholder="佐藤" onChange={onChangeLastName} />
            </FormControl>
            <FormControl>
              <FormLabel>名</FormLabel>
              <Input placeholder="一郎" onChange={onChangeFirstName} />
            </FormControl>
          </HStack>
          <HStack>
            <FormControl>
              <FormLabel>姓(ローマ字)</FormLabel>
              <Input placeholder="Sato" onChange={onChangeRomanLastName} />
            </FormControl>
            <FormControl>
              <FormLabel>名(ローマ字)</FormLabel>
              <Input placeholder="Ichiro" onChange={onChangeRomanFirstName} />
            </FormControl>
          </HStack>
          <FormControl>
            <FormLabel>生年月日</FormLabel>
            <Input onChange={onChangeBirthday} type="date" />
          </FormControl>
          <HStack>
            <FormControl>
              <FormLabel>性別</FormLabel>
              <Select onChange={onChangeGender}>
                <option>男</option>
                <option>女</option>
              </Select>
            </FormControl>
            <FormControl>
              <FormLabel>年齢</FormLabel>
              <Input placeholder="0" onChange={onChangeAge} type="number" />
            </FormControl>
          </HStack>
          <FormControl>
            <FormLabel>TEL</FormLabel>
            <Input placeholder="000-0000-0000" onChange={onChangePhoneNumber} />
          </FormControl>
          <FormControl>
            <FormLabel>Email</FormLabel>
            <Input placeholder="hogehoge@hugaa.com" onChange={onChangeEmail} />
          </FormControl>
          <FormControl>
            <FormLabel>雇用形態</FormLabel>
            <Select onChange={onChangeWorkingForm}>
              {workingFormNameList.map((workingForm) => (
                <option key={workingForm}>{workingForm}</option>
              ))}
            </Select>
          </FormControl>
          <FormControl>
            <FormLabel>雇用開始日</FormLabel>
            <Input onChange={onChangeEmploymentDate} type="date" />
          </FormControl>
          <PrimaryButton onClick={onClickRegistry}>登録</PrimaryButton>
        </Stack>
      </Box>
    </>
  );
});
