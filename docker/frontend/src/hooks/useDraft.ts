import { useCallback, useState } from "react";
import { instance } from "../api/axios";
import { typeDraft } from "../type/typeDraft";

export const useDraft = () => {
  const [loading, setLoading] = useState<boolean>(false);
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
    setLoading(true);
    instance
      .get("shift/draft")
      .then((res) => {
        setDraft(res.data);
      })
      .catch(() => {})
      .finally(() => setLoading(false));
  }, []);
  return { draft, getDraft, loading };
};
