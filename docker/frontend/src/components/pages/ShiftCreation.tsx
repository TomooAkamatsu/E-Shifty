import { Box, HStack, Text, useDisclosure } from "@chakra-ui/react";
import { memo, useCallback, useEffect, VFC } from "react";
import { useHistory } from "react-router-dom";
import { instance } from "../../api/axios";
import { useDraft } from "../../hooks/useDraft";
import { PrimaryButton } from "../atoms/button/PrimaryButton";
import { ShiftConfirmModal } from "../organisms/shift/shiftTable/ShiftConfirmModal";
import { ShiftTable } from "../organisms/shift/shiftTable/ShiftTable";

export const ShiftCreation: VFC = memo(() => {
  const history = useHistory();
  const { isOpen, onClose } = useDisclosure();
  const { draft, getDraft, loading } = useDraft();

  const onClickBack = useCallback(() => {
    history.push("/shift");
  }, [history]);

  const onClickRequestList = useCallback(() => {
    history.push("/shift/request/list");
  }, [history]);

  const onClickConfirm = () => {
    alert("後ほど実装");
  };

  const onClickRecreate = useCallback(() => {
    instance
      .get("/shift/draft/recreation")
      .then((res) => {
        console.log(res.data);
      })
      .catch(() => {});
    getDraft();
    history.push("/shift/draft/redirect");
  }, [history, getDraft]);

  useEffect(() => {
    getDraft();
  }, [getDraft]);

  const nextMonth = new Date(
    new Date().getFullYear(),
    new Date().getMonth() + 1
  );

  return (
    <Box align="center" p={5}>
      <HStack w="100vw" alignItems="center" pb={2}>
        <Box float="left" w="200px">
          <PrimaryButton onClick={onClickRecreate}>
            シフトを作り直す
          </PrimaryButton>
        </Box>
        <Box textAlign="center" float="left" px={10} py={2} w="70vw">
          <Text fontSize="3xl">{`翌月(${nextMonth.getFullYear()}年${
            nextMonth.getMonth() + 1
          }月)のシフト作成`}</Text>
        </Box>
        <Box w="280px" mr="40px">
          <PrimaryButton onClick={onClickRequestList}>
            提出済みの休み希望一覧を確認
          </PrimaryButton>
        </Box>
        {/* <PrimaryButton onClick={onOpen}>シフトを確定</PrimaryButton> */}
      </HStack>
      <ShiftTable draft={draft} loading={loading} />
      <PrimaryButton onClick={onClickBack}>シフト一覧へ戻る</PrimaryButton>
      <ShiftConfirmModal
        isOpen={isOpen}
        onClose={onClose}
        onClickConfirm={onClickConfirm}
      />
    </Box>
  );
});
