export const useDateList = () => {
  const today = new Date();
  const biginningOfNextMonth = new Date(
    today.getFullYear(),
    today.getMonth() + 1,
    1
  );
  const mid15thOfNextMonth = new Date(
    today.getFullYear(),
    today.getMonth() + 1,
    15
  );
  const mid16thOfNextMonth = new Date(
    today.getFullYear(),
    today.getMonth() + 1,
    16
  );
  const endOfNextMonth = new Date(today.getFullYear(), today.getMonth() + 2, 0);

  const firstHalfDateList = [];

  for (
    var x = biginningOfNextMonth;
    x <= mid15thOfNextMonth;
    x.setDate(x.getDate() + 1)
  ) {
    firstHalfDateList.push(new Date(x));
  }

  const latterHalfDateList = [];
  for (
    var y = mid16thOfNextMonth;
    y <= endOfNextMonth;
    y.setDate(y.getDate() + 1)
  ) {
    latterHalfDateList.push(new Date(y));
  }

  return { firstHalfDateList, latterHalfDateList };
};
