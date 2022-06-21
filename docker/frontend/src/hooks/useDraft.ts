import { useCallback, useState } from "react";
import { instance } from "../api/axios";
import { typeDraft } from "../type/typeDraft";

export const useDraft = () => {
  const [draft, setDraft] = useState<typeDraft>({
    firstHalf: [
      {
        employeeName: "",
        shift: {},
      },
    ],
    latterHalf: [
      {
        employeeName: "",
        shift: {},
      },
    ],
  });

  const getDraft = useCallback(() => {
    instance
      .get("shift/draft")
      .then((res) => {
        setDraft(res.data);
      })
      .catch(() => {});
  }, []);
  return { draft, getDraft };
};
