import math
from random import random, randint


MAX = 'max'
MIN = 'min'
CENTER = 'center'
FITNESS_TYPES = [MAX, MIN, CENTER]

SCALING_LINEAR = 'linear'
SCALING_TRUNC = 'truncation'
SCALING_EXPONENTIAL = 'exponential'
SCALING_LOG = 'logarithmic'

SCALING_TYPES = [SCALING_LINEAR, SCALING_TRUNC, SCALING_EXPONENTIAL,
                SCALING_LOG]


class FitnessList(list):
    """
    This class maintains a list of fitness values per generation.  It is a
    subclassed list to maintain information regarding whether fitness values
    should be maximized, minimized or centered around zero.  By holding that
    information, when the fitness list is given to a fitness evaluation or
    replacement object, it can configure itself automatically to conform to the
    appropropriate characteristics for the class.

    """

    def __init__(self, fitness_type, target_value=0.0):
        """
        This function initializes and accepts the fitness type.  Optionally it
        accepts the target value.  The target value is the value where
        execution of the evolutionary process halts upon attaining the goal.

        """

        list.__init__(self)
        self._fitness_type = None
        self._target_value = target_value
        self.set_fitness_type(fitness_type)
        self.set_target_value(target_value)

    def set_fitness_type(self, fitness_type):
        """
        This function sets the fitness type.

        Accepted fitness types are 'min', 'max', or 'center'.

        """

        if fitness_type not in FITNESS_TYPES:
            raise ValueError("""
                Fitness type must be either min, max, or
                center, not %s""" % (fitness_type))

        self._fitness_type = fitness_type

    def get_fitness_type(self):
        """
        This function returns the fitness type, such as 'min',
        'max', or 'center'.

        """

        return self._fitness_type

    def set_target_value(self, target_value):
        """
        This function sets the target value.

        """

        if not isinstance(target_value, float):
            raise ValueError("The target value must be a float")
        self._target_value = target_value

    def get_target_value(self):
        """
        This function returns the target value.

        """

        return self._target_value

    def min_value(self):
        """
        This function returns the minimum value in the list.

        """

        values = [value for value in self]
        values.sort()
        return min(values)[0]

    def max_value(self):
        """
        This function returns the maximum value in the list.

        """

        values = [value for value in self]
        return max(values)[0]

    def best_value(self):
        """
        This function returns the best value in the list on the basis of the
        objective of the fitness list. For example, when trying to maximize a
        fitness value, it would return the maximum value.

        """

        if self._fitness_type == MIN:
            return self.min_value()
        elif self._fitness_type == MAX:
            return self.max_value()
        elif self._fitness_type == CENTER:
            sortlist = self.sorted()
            return sortlist[0][0]

    def worst_value(self):
        """
        This function returns the worst value in the list on the basis of the
        objective of the fitness list. For example, when trying to maximize a
        fitness value, it would return the minimum value.

        """

        if self._fitness_type == MIN:
            return self.max_value()
        elif self._fitness_type == MAX:
            return self.min_value()
        elif self._fitness_type == CENTER:
            sortlist = self.sorted()
            return sortlist[-1][0]

    def min_member(self):
        """
        This function returns the member with the minimum value.

        """

        if self._fitness_type == MIN:
            return self.sorted()[0][1]
        elif self._fitness_type == MAX:
            return self.sorted()[-1][1]
        elif self._fitness_type == CENTER:
            return self.sorted()[0][1]

    def max_member(self):
        """
        This function returns the member with the maximum value.

        """

        if self._fitness_type == MIN:
            return self.sorted()[-1][1]
        elif self._fitness_type == MAX:
            return self.sorted()[0][1]
        elif self._fitness_type == CENTER:
            return self.sorted()[-1][1]

    def best_member(self):
        """
        This function returns the member with the best value based upon the
        criteria of the fitness type.

        """

        if self._fitness_type == MIN:
            return self.min_member()
        elif self._fitness_type == MAX:
            return self.max_member()
        elif self._fitness_type == CENTER:
            return self.min_member()

    def worst_member(self):
        """
        This function returns the member with the worst value based upon the
        criteria of the fitness type.

        """

        if self._fitness_type == MIN:
            return self.max_member()
        elif self._fitness_type == MAX:
            return self.min_member()
        elif self._fitness_type == CENTER:
            return self.max_member()

    def mean(self):
        """
        This function returns the mean fitness value.

        """

        total = 0.0
        for item in self:
            total += item[0]
        return total / float(len(self))

    def median(self):
        """
        This function returns the median fitness value.

        """

        sort_list = self.sorted()
        length = len(self)
        half = int(length / 2)
        if half - length % 2 == 0:
            return (sort_list[half - 1][0] + sort_list[half][0]) / 2.0
        else:
            return sort_list[half][0]

    def stddev(self):
        """
        This function returns the standard deviation of fitness values.

        """

        total = 0.0
        mean = self.mean()
        for item in self:
            total += pow(item[0] - mean, 2.0)
        return pow(total / (float(len(self)) - 1.0), .5)

    def sorted(self):
        """
        This function returns the fitness list sorted in fitness order
        according to the fitness type.

        """

        if self._fitness_type == MIN:
            new_list = [i for i in self]
            new_list.sort()
        elif self._fitness_type == MAX:
            new_list = [i for i in self]
            new_list.sort(reverse=True)
        elif self._fitness_type == CENTER:
            new_list = [[abs(i[0] - self._target_value), i[1]] for i in self]
            new_list.sort()
            new_list = [[self[i[1]][0], i[1]] for i in new_list]
        return new_list


class Selection(object):
    """
    This is the base class for methods appropriate for assessing the fitness
    landscape.  _selection_type refers to the technique associated with the
    selection method that is subclassed.

    The selection_list used in this class is an ordinary list of values, not a
    fitness list, the fitness list being a list of tuples.

    It is the responsibility of the derived classes to adjust the selection
    list values as needed.

    """

    def __init__(self, selection_list=None):
        self._selection_type = MAX
        if selection_list:
            self.set_selection_list(selection_list)
        else:
            self._selection_list = None

    def set_selection_list(self, selection_list):
        """
        This function accepts the selection list.  This is the list of fitness
        values that may have been transformed for the selection process.

        """

        if not isinstance(selection_list, list):
            raise ValueError("Selection list is not a list")
        if isinstance(selection_list, FitnessList):
            raise ValueError("Selection list should not be a Fitness List")
        self._selection_list = selection_list

    def set_selection_type(self, selection_type):
        """
        This function accepts the selection type, which must be either 'min'
        or 'max'.  The selection type is used by the subclass to know how to
        manipulate the list to achieve the fitness goals.

        """

        if selection_type not in (MIN, MAX):
            raise ValueError("""
                The selection type must be either '%s' or '%s', not '%s'.
                    """ % (MIN, MAX, selection_type))
        self._selection_type = selection_type

    @staticmethod
    def _roulette_wheel(scale_list):
        """
        This function receives a list that has been scaled so that the sum
        total of the list is 1.0.  This enables a fair use of probability.
        This is a generator that yields a random selection from the list.

        """

        if round(sum(scale_list), 10) != 1.0:
            raise ValueError(
                "The scaled list received does not total 1.0: %s" % (
                    sum(scale_list)))
        cumu = [0.0]
        length = len(scale_list)
        for i in xrange(length):
            cumu.append(scale_list[i] + cumu[-1])

        #   Rewrite for binary search sometime
        for i in xrange(length):
            rand_val = random()
            position = 0
            while position < length:
                if cumu[position + 1] > rand_val:
                    yield position
                    break
                position += 1

    def _make_sort_list(self):
        """
        This function sorts the _selection list making it similar to the
        original fitness list, except the the fitness values have been
        adjusted.

        """

        length = len(self._selection_list)
        sort_list = []
        for i in xrange(length):
            sort_list.append([self._selection_list[i], i])
        return sort_list


class Tournament(Selection):
    """
    Selects random tuples and returns either the minimum or maximum.  To speed
    up the rate of selection, use larger tournament sizes, and smaller to slow
    down the process.

    """

    def __init__(self, selection_list=None, tournament_size=None):
        Selection.__init__(self, selection_list)
        self._tournament_size = None
        self.set_tournament_size(tournament_size)
        self._minmax = None

    def set_tournament_size(self, tournament_size):
        """
        This function accepts the tournament size, which is the number of
        members that will be selected per tournament.

        """

        if tournament_size is not None:
            if not isinstance(tournament_size, int):
                raise ValueError("Tournament size, %s must be an int." % (
                    tournament_size))
            if self._selection_list:
                if tournament_size > len(self._selection_list):
                    raise ValueError("""The tournament size, %s, cannot
                        be larger than the population, %s.""" % (
                        tournament_size, len(self._selection_list)))
        self._tournament_size = tournament_size

    def _set_minmax(self, minmax):
        """
        This function sets whether the selection should minimize or maximize.
        """

        if minmax not in [MIN, MAX]:
            raise ValueError("Must be either '%s' or '%s'" % (MIN, MAX))
        self._minmax = minmax

    def select(self):
        """
        The select function provides all the members based upon the class
        algorithm.

        """
        population_size = len(self._selection_list)
        position = 0
        while position < population_size:

            choice = 0

            #   Grabs members until reaching the tournament size
            tourn_list = FitnessList(self._minmax)
            while choice < self._tournament_size:
                rand_position = randint(0, population_size - 1)
                
                #   Lookup the fitness value
                tourn_list.append([self._selection_list[rand_position],
                                   rand_position])
                choice += 1
            yield tourn_list.best_member()
            del tourn_list
            position += 1


class Fitness(Selection):
    """
    This class is the prototype for the fitness functions.  The primary job of
    a fitness function is to deal with the list of [fitness value, member no]
    pairs that are generated as a result of a run.

    It also scales and configures the fitness values to be consistent with the
    fitness functions characteristics. For example, if the fitness strategy is
    to minimize values and the Fitness selection strategy maximizes, then the
    fitness values will be converted to -value.  If the fitness strategy
    centers on a value, such as zero, then the values will be converted to the
    absolute distance from that value.

    """

    def __init__(self, fitness_list):
        Selection.__init__(self)
        self._fitness_list = None
        self.set_fitness_list(fitness_list)

    def set_fitness_list(self, fitness_list):
        """
        This function accepts the fitness list.  It is the list of fitness
        values by member number for a population.

        """

        if not isinstance(fitness_list, FitnessList):
            raise ValueError("Fitness_list is not a list")

        self._fitness_list = fitness_list

        if fitness_list.get_fitness_type() == CENTER:
            #   Convert to absolute distance from the target_value
            self._selection_list = []
            length = len(fitness_list)
            target_value = fitness_list.get_target_value()
            for i in xrange(length):
                self._selection_list.append(
                        abs(fitness_list[i][0] - target_value))
        else:
            self._selection_list = [item[0] for item in fitness_list]

    @staticmethod
    def _invert(value):
        """
        This method returns the reciprocal of the value.

        """
        if value == 0.0:
            return 0.0
        else:
            return 1.0 / value

    def _scale_list(self):
        """
        This function scales the list to convert for example min to max where
        the selection type warrants it.

        """
        seltype = self._selection_type
        fit_type = self._fitness_list.get_fitness_type()

        if seltype == MAX and \
            fit_type == MIN:

            inverse = True

        elif seltype == MIN and \
            fit_type == MAX:

            inverse = True

        elif seltype == MAX and \
            fit_type == CENTER:

            inverse = True

        else:
            inverse = False

        if inverse:
            self._selection_list = [self._invert(value)
                for value in self._selection_list]

    @staticmethod
    def _make_prob_list(selection_list):
        """
        This function aids in calculating probability lists. It
        scales the values to add up to 1.0.

        """

        total = sum(selection_list)
        if total != 0.0:
            return [value / total for value in selection_list]
        else:
            return selection_list


class FitnessProportionate(Fitness):
    """
    The probability of selection is based upon the fitness value of the
    individual relative to the rest of the population.

    Pr(G_i) = f(G_i)/ sum(1 to pop) f(G_i)

    This is the total probability.  Roulette wheel selects individuals
    randomly, highest fitness more likely to be included.

    Note that inherent in this approach is the assumption that the fitness
    value should be as large as possible. Consequently, if the fitness type
    is MIN or CENTER, a modification is made.  In the case of MIN, inverse of
    the fitness values are used. For the CENTER fitness type, the inverse of
    the absolute difference between the target value and the fitness values are
    used.

    The available scaling methods are:

    *    SCALING_LINEAR = 'linear'
    *    SCALING_TRUNC = 'truncation'
    *    SCALING_EXPONENTIAL = 'exponential'
    *    SCALING_LOG = 'logarithmic'

    Truncation can be selected via
        ex: self.select(param=<your truncation value>).  Upon truncation,
    the fitness values are linearly scaled.

    The default value for exponential is 2.0, but that is adjustable via:
        ex: self.select(param=1.5)

    Also, note that because a probability map is derived from the scaled
    numbers, the fitness values must all be positive or all negative for this
    approach to be valid.  In addition, the logarithmic scaling also requires
    that the fitness values must be 1 or above, prior to scaling.  This is
    because the log below 1 is a negative number, which then interferes with
    the calculation of the probabilities.

    """

    def __init__(self, fitness_list, scaling_type):
        Fitness.__init__(self, fitness_list)
        self._scaling_type = None
        self.set_scaling_type(scaling_type)
        self._check_minmax()

    def set_scaling_type(self, scaling_type):
        """
        This function accepts the type of scaling that will be performed on
        the data in preparation of building a probability list for the roulette
        selection.

        """
        if not scaling_type in SCALING_TYPES:
            raise ValueError(
                "Invalid scaling type: %s, valid scaling types are %s" % (
                    scaling_type, SCALING_TYPES))
        self._scaling_type = scaling_type

    def _check_minmax(self):
        """
        If selection of fitness is with proportions, then they must all be the
        same sign.  Also, negative numbers do not mix with logs.

        """

        if min(self._selection_list) < 0.0 < max(self._selection_list):
            raise ValueError("Inconsistent signs in selection list")

        if min(self._selection_list) < 0.0 and \
                self._scaling_type == 'logarithmic':
            raise ValueError("Negative numbers cannot be used with logs.")

    def select(self, param=None):
        """
        The select function provides all the members based upon the class
        algorithm.

        """

        self._scale_list()
        #   Now apply to the roulette wheel
        return self._roulette_wheel(self._apply_prop_scaling(param))

    def _apply_prop_scaling(self, param):
        """
        This function scales the values according to the scale type.
        """

        if not self._selection_list:
            raise ValueError("No fitness list to scale")

        if self._scaling_type == SCALING_LINEAR:
            self._selection_list = self._make_prob_list(self._selection_list)

        elif self._scaling_type == SCALING_EXPONENTIAL:
            if param is None:
                exponent = 2.0
            else:
                exponent = param

            self._selection_list = [pow(item, exponent)
                for item in self._selection_list]
            self._selection_list = self._make_prob_list(self._selection_list)

        elif self._scaling_type == SCALING_LOG:
            self._selection_list = [math.log(item)
                for item in self._selection_list]
            self._selection_list = self._make_prob_list(self._selection_list)

        elif self._scaling_type == SCALING_TRUNC:
            if param:
                trunc = param
            else:
                raise ValueError("""
                    Truncation scaling requires a truncation value""")
            length = len(self._selection_list)
            for i in xrange(length):
                if self._selection_list[i] < trunc:
                    self._selection_list[i] = 0.0

            self._selection_list = self._make_prob_list(self._selection_list)
        else:
            #   theoretically the raise error would prevent this
            pass

        return self._selection_list


class FitnessTournament(Fitness, Tournament):
    """
    This class selects the fitness based on a tournament. It defaults to a
    tournament size of 2, but it can be anything up to the size of the
    population.

    """

    def __init__(self, fitness_list, tournament_size=2):

        Tournament.__init__(self)
        Fitness.__init__(self, fitness_list)
        if self._fitness_list.get_fitness_type() == MAX:
            minmax = MAX
        else:
            minmax = MIN
        self._set_minmax(minmax)
        self.set_tournament_size(tournament_size)


class FitnessElites(Fitness):
    """
    This class selects the highest or lowest fitness depending upon what is
    desired.  The fitness list is put into the selection list in this case so
    that once the sorting for rank takes place, the member numbers are still
    available.

    """

    def __init__(self, fitness_list, rate):
        Fitness.__init__(self, fitness_list)
        self._rate = None
        self.set_rate(rate)
        self.set_selection_type(fitness_list._fitness_type)

    def set_rate(self, rate):
        """
        This function accepts a value greater than 0 and less than or equal to
        1.0.  It is the percentage of members from a list sorted by best
        values.

        """

        if not (isinstance(rate, float) and (0.0 < rate <= 1.0)):
            raise ValueError(
                "The rate, %s, should between 0.0 and 1.0" % (
                    rate))
        self._rate = rate

    def select(self):
        """
        The select function provides all the members based upon the class
        algorithm.

        """

        self._scale_list()
        sort_list = self._make_sort_list()
        if self._fitness_list._fitness_type == MAX:
            sort_list.sort(reverse=True)
        else:
            sort_list.sort()
        elites = int(round(self._rate * float(len(sort_list))))
        for item in sort_list[:elites]:
            yield item[1]


class FitnessLinearRanking(Fitness):
    """
    This class selects fitness on the basis of rank with other members.  Only
    the position in the ranking matters rather than the fitness value.  The
    probability curve is calculated on that basis. Then, roulette selection
    takes place.

    This uses the formula for the probability curve of:
    Probability = 1 / population * (worstfactor + (best -
    worstfactor) * (rank(Member) - 1) / (population - 1))

    where best = 2.0 - worstfactor

    the best individual produces up to twice the children as the average.

    To make sense of this:
    Suppose that the worst factor is .6 and therefor the best factor is
    1.4.  That will generate a probability list where the most fit members
    will be weighted more highly than less fit members.

    If the worst factor is 1.0 and therefore the best factor is 1.0 too,
    the slope of the probability line will flat.

    And finally, if the worst factor is 1.6 and the best factor is .4, the
    slope of the probaility line will cause the less fit members to have a
    higher probability of being selected than the more fit members.

    """

    def __init__(self, fitness_list, worstfactor):
        Fitness.__init__(self, fitness_list)
        self._worstfactor = None
        self.set_worstfactor(worstfactor)

    def set_worstfactor(self, worstfactor):
        """
        This function sets the worst factor.  See the class description for
        more.

        """
        if not isinstance(worstfactor, float):
            raise ValueError(
                "Worstfactor must be a float value between 0 and 2.0.")
        if (worstfactor < 0) or (worstfactor > 2.0):
            raise ValueError(
                "Worstfactor must be a float value between 0 and 2.0.")
        self._worstfactor = worstfactor

    def select(self):
        """
        The select function provides all the members based upon the class
        algorithm.

        """

        self._scale_list()

        sort_list = self._make_sort_list()

        sort_list.sort()
        length = len(sort_list)
        prob_list = self._linear_ranking(
                            length, self._worstfactor)

        #   Now this list needs the probabilities combined with members
        select_list = [[sort_list[i][1], prob_list[i]]
                        for i in xrange(length)]

        return self._roulette_wheel([item[1] for item in select_list])

    @staticmethod
    def _linear_ranking(count, worst):
        """
        This applies the best and worst factors and assigns the selection
        probability to each rank.

        This returns a list of those probabilities.

        """

        best = 2.0 - worst
        scale_list = []
        i = 1.0
        count = float(count)
        while i < count + 1.0:
            value = (worst + (best - worst) * (i - 1.0) / (count - 1.0))
            value /= count
            scale_list.append(value)
            i += 1.0
        return scale_list


class FitnessTruncationRanking(Fitness):
    """
    This class selects fitness on the basis of rank with other members if
    above a certain rank.  Once above that rank, any member can be selected
    with an equal probability.  The truncation value is entered as a rate and
    converted to a ranking value. For example, if a population has 100 members
    and a truncation value of .2, the truncated ranking will be converted to a
    rank of 20.

    """

    def __init__(self, fitness_list, trunc_rate):
        Fitness.__init__(self, fitness_list)
        self._trunc_rate = None
        self.set_trunc_rate(trunc_rate)
        self.set_selection_type(MIN)

    def set_trunc_rate(self, trunc_rate):
        """
        This function sets the rate, between 0 and 1 that is a hurdle for
        selection.

        """

        if not isinstance(trunc_rate, float):
            raise ValueError(
                "Trunc_rate, %s, should between 0.0 and 1.0" % (trunc_rate))
        if (trunc_rate < 0) or (trunc_rate >= 1.0):
            raise ValueError(
                "Trunc_rate, %s, should between 0.0 and 1.0" % (trunc_rate))
        self._trunc_rate = trunc_rate

    def select(self):
        """
        The select function provides all the members based upon the class
        algorithm.

        """

        self._scale_list()
        sort_list = self._make_sort_list()
        sort_list.sort(reverse=True)

        length = len(sort_list)
        cutoff_rank = int(round(self._trunc_rate * length))
        prob_list = []
        prob = self._calc_prob(length, cutoff_rank)

        for i in xrange(length):
            member_no = sort_list[i][1]
            if i < cutoff_rank - 1:
                prob_list.append([member_no, prob])
            else:
                prob_list.append([member_no, 0.0])

        return self._roulette_wheel([item[1] for item in prob_list])

    @staticmethod
    def _calc_prob(length, cutoff_rank):
        """
        This function calculates the probability that a member will be selected
        if it falls within the cutoff rank.
        """

        return 1.0 / float(length - cutoff_rank)


class Replacement(Fitness):
    """
    This is the base class for the classes that identify which members are to
    be replaced.  It is basically the same as a fitness class, but attempts to
    identify the worst, not the best.

    """

    def __init__(self, fitness_list):
        Fitness.__init__(self, fitness_list)
        self._replacement_count = 0


class ReplacementTournament(Replacement, Tournament):
    """
    This class selects the fitness based on a tournament.

    """

    def __init__(self, fitness_list, tournament_size):
        """
        This class combines Replacement and Tournament.  It inits Replacement
        first, because both have a select function and the Tournament select
        function is the one that matters.

        """

        Replacement.__init__(self, fitness_list)
        #Tournament.__init__(self)
        self.set_tournament_size(tournament_size)
        self.set_fitness_list(fitness_list)
        if self._fitness_list.get_fitness_type() == MAX:
            minmax = MIN
        else:
            minmax = MAX
        self._set_minmax(minmax)
        self._scale_list()
