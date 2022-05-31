import { Box, Stack, useDisclosure } from "@chakra-ui/react";
import { memo, useCallback, VFC } from "react";
import { useHistory } from "react-router-dom";
import { PrimaryButton } from "../atoms/button/PrimaryButton";
import { ShiftConfirmModal } from "../organisms/shift/shiftTable/ShiftConfirmModal";
import { ShiftTable } from "../organisms/shift/shiftTable/ShiftTable";

export const ShiftCreation: VFC = memo(() => {
  const history = useHistory();
  const { isOpen, onOpen, onClose } = useDisclosure();

  const onClickBack = useCallback(() => {
    history.push("/shiftwork_management/shift");
  }, [history]);

  const onClickRequestList = useCallback(() => {
    history.push("/shiftwork_management/shift/request/list");
  }, [history]);

  const onClickConfirm = () => {
    alert("後ほど実装");
  };

  return (
    <Box align="center" p={5}>
      <Stack spacing={5}>
        <Box align="right" w="100vw" px={10}>
          <Box textAlign="left" float="left">
            <h1>自動的に休み希望を反映したシフトが作成されています</h1>
            <h1>手動で変更して確定ボタンを押してください。</h1>
          </Box>
          <PrimaryButton onClick={onClickRequestList}>
            提出済みのシフト一覧を確認
          </PrimaryButton>
          <PrimaryButton onClick={onOpen}>シフトを確定</PrimaryButton>
        </Box>
        <ShiftTable />
      </Stack>
      <PrimaryButton onClick={onClickBack}>シフト一覧へ戻る</PrimaryButton>
      <ShiftConfirmModal
        isOpen={isOpen}
        onClose={onClose}
        onClickConfirm={onClickConfirm}
      />
    </Box>
  );
});
