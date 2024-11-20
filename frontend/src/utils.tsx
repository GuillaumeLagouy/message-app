const formatDate = (dateString: string) => {
  const date = new Date(dateString);
  const day = date.getDate().toString().padStart(2, '0'); // Jour avec deux chiffres
  const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Mois (janvier = 0)
  const year = date.getFullYear();

  const hours = date.getHours().toString().padStart(2, '0'); // Heures avec deux chiffres
  const minutes = date.getMinutes().toString().padStart(2, '0'); // Minutes avec deux chiffres

  return `${day}-${month}-${year} ${hours}:${minutes}`;
};

export { formatDate };
