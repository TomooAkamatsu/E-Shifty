import { memo, useCallback, VFC } from "react";
import { useHistory } from "react-router-dom";
import { PrimaryButton } from "../atoms/button/PrimaryButton";

export const Shift: VFC = memo(() => {
  const history = useHistory();
  const onClickRequest = useCallback(
    () => history.push("/shiftwork_management/shift/request"),
    [history]
  );

  return (
    <>
      <p>Shiftページですaaaa</p>
      <PrimaryButton onClick={onClickRequest}>休み希望の提出確認</PrimaryButton>
    </>
  );
});
