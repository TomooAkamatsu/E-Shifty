import { FormControl, FormLabel, Input, Stack } from "@chakra-ui/react";
import { memo, VFC } from "react";
// import { useHistory } from "react-router-dom";

export const ShiftCreation: VFC = memo(() => {
  // const history = useHistory();

  // const onClickBack = useCallback(() => {
  //   history.push("/shiftwork_management/employees");
  // }, [history]);

  return (
    <>
      <Stack>
        <FormControl>
          <FormLabel>aaaaa</FormLabel>
          <Input></Input>
        </FormControl>
      </Stack>
    </>
  );
});

/* <Box align="right" pr={30} pt={3}>
<PrimaryButton onClick={onClickBack}>従業員一覧に戻る</PrimaryButton>);
</Box> */
